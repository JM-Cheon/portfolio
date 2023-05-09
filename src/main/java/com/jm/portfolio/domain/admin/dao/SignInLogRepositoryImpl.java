package com.jm.portfolio.domain.admin.dao;

import com.jm.portfolio.domain.admin.dto.response.SignInLogResponse;
import com.jm.portfolio.global.common.paging.SearchCondition;
import com.jm.portfolio.global.error.exception.InvalidValueException;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.jm.portfolio.domain.admin.domain.QSignInLog.signInLog;

@Slf4j
@RequiredArgsConstructor
public class SignInLogRepositoryImpl implements SignInLogRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private OrderSpecifier<?> signInLogSort(Pageable page) {
        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
        if (!page.getSort().isEmpty()) {
            //정렬값이 들어 있으면 for 사용하여 값을 가져온다
            for (Sort.Order order : page.getSort()) {
                // 서비스에서 넣어준 DESC or ASC 를 가져온다.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()){
                    case "email":
                        return new OrderSpecifier(direction, signInLog.email);
                    case "signInIp":
                        return new OrderSpecifier(direction, signInLog.signInIp);
                    case "signInAt":
                        return new OrderSpecifier(direction, signInLog.signInAt);
                    default:
                        return new OrderSpecifier(Order.DESC, signInLog.idx);
                }
            }
        }
        return new OrderSpecifier(Order.DESC, signInLog.idx);
    }

    private BooleanExpression containsEmail(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("email")) {
            return null;
        }
        if(StringUtils.isEmpty(searchCondition.getSearchValue())) {
            return null;
        }
        return signInLog.email.value.contains(searchCondition.getSearchValue());
    }

    private BooleanExpression containsSignInIp(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("signInIp")) {
            return null;
        }
        if(StringUtils.isEmpty(searchCondition.getSearchValue())) {
            return null;
        }
        return signInLog.signInIp.contains(searchCondition.getSearchValue());
    }

    private BooleanExpression searchSignInAt (SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("signInAt")) {
            return null;
        }
        if(searchCondition.getStartDate() != null && searchCondition.getEndDate() != null) {
            if(searchCondition.getStartDate().isAfter(searchCondition.getEndDate())) {
                throw new InvalidValueException(searchCondition.getStartDate().toString() +  " || " + searchCondition.getEndDate().toString());
            }
            return signInLog.signInAt.between(searchCondition.getStartDate(), searchCondition.getEndDate());
        }
        if(searchCondition.getStartDate() != null) {
            return signInLog.signInAt.goe(searchCondition.getStartDate());
        }
        if(searchCondition.getEndDate() != null) {
            return signInLog.signInAt.loe(searchCondition.getEndDate());
        }
        return null;
    }

    @Override
    public Long maxSignInLogIdx() {
        return jpaQueryFactory
                .select(signInLog.idx.max())
                .from(signInLog)
                .fetchOne();
    }

    @Override
    public Page<SignInLogResponse> getSignInLogList(Pageable page, SearchCondition searchCondition) {
        List<SignInLogResponse> content = jpaQueryFactory
                .select(Projections.fields(SignInLogResponse.class,
                        signInLog.idx,
                        signInLog.email,
                        signInLog.signInIp,
                        signInLog.signInAt))
                .from(signInLog)
                .where(
                        containsEmail(searchCondition),
                        containsSignInIp(searchCondition),
                        searchSignInAt(searchCondition)
                )
                .offset(page.getOffset())
                .limit(page.getPageSize())
                .orderBy(signInLogSort(page))
                .fetch();

        return new PageImpl<>(content, page, maxSignInLogIdx());
    }
}
