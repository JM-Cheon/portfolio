package com.jm.portfolio.domain.users.dao;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
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

import static com.jm.portfolio.domain.users.domain.QUsers.users;

@Slf4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    private OrderSpecifier<?> userSort(Pageable page) {
        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
        if (!page.getSort().isEmpty()) {
            //정렬값이 들어 있으면 for 사용하여 값을 가져온다
            for (Sort.Order order : page.getSort()) {
                // 서비스에서 넣어준 DESC or ASC 를 가져온다.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()){
                    case "email":
                        return new OrderSpecifier(direction, users.email);
                    case "nickname":
                        return new OrderSpecifier(direction, users.nickname);
                    case "createdAt":
                        return new OrderSpecifier(direction, users.createdAt);
                    case "lastUpdatedAt":
                        return new OrderSpecifier(direction, users.lastUpdatedAt);
                    case "withdrawAt":
                        return new OrderSpecifier(direction, users.withdrawAt);
                }
            }
        }
        return new OrderSpecifier(Order.DESC, users.email);
    }

    private BooleanExpression containsEmail(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("email")) {
            return null;
        }
        if(StringUtils.isEmpty(searchCondition.getSearchValue())) {
            return null;
        }
        return users.email.value.contains(searchCondition.getSearchValue());
    }

    private BooleanExpression containsNickname(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("nickname")) {
            return null;
        }
        if(StringUtils.isEmpty(searchCondition.getSearchValue())) {
            return null;
        }
        return users.nickname.contains(searchCondition.getSearchValue());
    }

    private BooleanExpression containsCreatedIp(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("createdIp")) {
            return null;
        }
        if(StringUtils.isEmpty(searchCondition.getSearchValue())) {
            return null;
        }
        return users.createdIp.contains(searchCondition.getSearchValue());
    }

    private BooleanExpression containsLastUpdatedIp(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("lastUpdatedIp")) {
            return null;
        }
        if(StringUtils.isEmpty(searchCondition.getSearchValue())) {
            return null;
        }
        return users.lastUpdatedIp.contains(searchCondition.getSearchValue());
    }

    private BooleanExpression containsWithdrawIp(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("withdrawIp")) {
            return null;
        }
        if(StringUtils.isEmpty(searchCondition.getSearchValue())) {
            return null;
        }
        return users.withdrawIp.contains(searchCondition.getSearchValue());
    }

    private BooleanExpression isWithdrawYn(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("isWithdraw")) {
            return null;
        }
        if(StringUtils.isEmpty(searchCondition.getSearchValue())) {
            return null;
        }
        return users.isWithdraw.eq(searchCondition.getSearchValue());
    }

    private BooleanExpression isExpiredYn(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("isExpired")) {
            return null;
        }
        if(StringUtils.isEmpty(searchCondition.getSearchValue())) {
            return null;
        }
        return users.isExpired.eq(searchCondition.getSearchValue());
    }

    private BooleanExpression isDisabledYn(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("isDisabled")) {
            return null;
        }
        if(StringUtils.isEmpty(searchCondition.getSearchValue())) {
            return null;
        }
        return users.isDisabled.eq(searchCondition.getSearchValue());
    }

    private BooleanExpression searchCreatedAt(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("createdAt")) {
            return null;
        }
        if(searchCondition.getStartDate() != null && searchCondition.getEndDate() != null) {
            if(searchCondition.getStartDate().isAfter(searchCondition.getEndDate())) {
                throw new InvalidValueException(searchCondition.getStartDate().toString() +  " || " + searchCondition.getEndDate().toString());
            }
            return users.createdAt.between(searchCondition.getStartDate(), searchCondition.getEndDate());
        }
        if(searchCondition.getStartDate() != null) {
            return users.createdAt.goe(searchCondition.getStartDate());
        }
        if(searchCondition.getEndDate() != null) {
            return users.createdAt.loe(searchCondition.getEndDate());
        }
        return null;
    }

    private BooleanExpression searchLastUpdatedAt(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("lastUpdatedAt")) {
            return null;
        }
        if(searchCondition.getStartDate() != null && searchCondition.getEndDate() != null) {
            if(searchCondition.getStartDate().isAfter(searchCondition.getEndDate())) {
                throw new InvalidValueException(searchCondition.getStartDate().toString() +  " || " + searchCondition.getEndDate().toString());
            }
            return users.lastUpdatedAt.between(searchCondition.getStartDate(), searchCondition.getEndDate());
        }
        if(searchCondition.getStartDate() != null) {
            return users.lastUpdatedAt.goe(searchCondition.getStartDate());
        }
        if(searchCondition.getEndDate() != null) {
            return users.lastUpdatedAt.loe(searchCondition.getEndDate());
        }
        return null;
    }

    private BooleanExpression searchWithdrawAt(SearchCondition searchCondition) {
        if(!searchCondition.getSearchBy().equals("withdrawAt")) {
            return null;
        }
        if(searchCondition.getStartDate() != null && searchCondition.getEndDate() != null) {
            if(searchCondition.getStartDate().isAfter(searchCondition.getEndDate())) {
                throw new InvalidValueException(searchCondition.getStartDate().toString() +  " || " + searchCondition.getEndDate().toString());
            }
            return users.withdrawAt.between(searchCondition.getStartDate(), searchCondition.getEndDate());
        }
        if(searchCondition.getStartDate() != null) {
            return users.withdrawAt.goe(searchCondition.getStartDate());
        }
        if(searchCondition.getEndDate() != null) {
            return users.withdrawAt.loe(searchCondition.getEndDate());
        }
        return null;
    }

    @Override
    public Long maxUserIdx() {
        return jpaQueryFactory
                .select(users.idx.max())
                .from(users)
                .fetchOne();
    }

    @Override
    public Users findByEmail(Email email) {
        return jpaQueryFactory
                .select(users)
                .from(users)
                .where(users.email.eq(email))
                .fetchOne();

        // entity가 아닌 dto로 조회
//        return jpaQueryFactory
//                .select(Projections.fields(
//                        UserResponse.class,
//                        Expressions.asString(email.toString()).as("email"),
//                        users.nickname,
//                        users.withdrawAt,
//                        users.withdrawIp,
//                        users.isWithdraw,
//                        users.isDisabled,
//                        users.isExpired))
//                .from(users)
//                .where(users.email.eq(email))
//                .fetchOne();
    }

    @Override
    public boolean existsByEmail(Email email) {
        Integer fetchOne = jpaQueryFactory
                .selectOne()
                .from(users)
                .where(users.email.eq(email))
                .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public Page<UserResponse> getUserList(Pageable page, SearchCondition searchCondition) {
        List<UserResponse> content = jpaQueryFactory
                .select(Projections.fields(UserResponse.class,
                        users.createdAt,
                        users.lastUpdatedAt,
                        users.createdIp,
                        users.lastUpdatedIp,
                        users.email,
                        users.nickname,
                        users.withdrawAt,
                        users.withdrawIp,
                        users.isWithdraw,
                        users.isDisabled,
                        users.isExpired))
                .from(users)
                .where(
                        containsEmail(searchCondition),
                        containsNickname(searchCondition),
                        containsCreatedIp(searchCondition),
                        containsLastUpdatedIp(searchCondition),
                        containsWithdrawIp(searchCondition),
                        isWithdrawYn(searchCondition),
                        isExpiredYn(searchCondition),
                        isDisabledYn(searchCondition),
                        searchCreatedAt(searchCondition),
                        searchLastUpdatedAt(searchCondition),
                        searchWithdrawAt(searchCondition)
                )
                .offset(page.getOffset())
                .limit(page.getPageSize())
                .orderBy(userSort(page))
                .fetch();

        return new PageImpl<>(content, page, maxUserIdx());
    }
}
