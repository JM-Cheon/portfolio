package com.jm.portfolio.domain.admin.dao;

import com.jm.portfolio.domain.admin.dto.response.AuthListResponse;
import com.jm.portfolio.domain.admin.dto.response.AuthResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.jm.portfolio.domain.admin.domain.QAuthority.authority;

@Slf4j
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int authCount() {
        return Math.toIntExact(jpaQueryFactory
                .select(authority.count())
                .from(authority)
                .fetchFirst());
    }

    @Override
    public AuthListResponse getAuthList() {
        List<AuthResponse> content = jpaQueryFactory
                .select(Projections.fields(AuthResponse.class,
                        authority.createdAt,
                        authority.lastUpdatedAt,
                        authority.createdIp,
                        authority.lastUpdatedIp,
                        authority.authCode,
                        authority.authName,
                        authority.authDesc))
                .from(authority)
                .orderBy(authority.authCode.asc())
                .fetch();

        return new AuthListResponse(authCount(), content);
    }
}
