package com.jm.portfolio.domain.users.dao;

import com.jm.portfolio.domain.users.domain.QUsers;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.jm.portfolio.domain.users.domain.QUsers.users;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long maxUserIdx() {
        return jpaQueryFactory
                .from(users)
                .select(users.idx.max())
                .fetchOne();
    }
}
