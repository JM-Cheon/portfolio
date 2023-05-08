package com.jm.portfolio.domain.users.dao;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.global.common.paging.SearchCondition;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
//                .where(searchEq(userSearch))
                .offset(page.getOffset())
                .limit(page.getPageSize())
                .orderBy(userSort(page))
                .fetch();

        return new PageImpl<>(content, page, maxUserIdx());
    }
}
