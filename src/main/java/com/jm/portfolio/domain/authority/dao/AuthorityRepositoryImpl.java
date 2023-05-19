package com.jm.portfolio.domain.authority.dao;

import com.jm.portfolio.domain.authority.dto.response.AuthorityListResponse;
import com.jm.portfolio.domain.authority.dto.response.AuthorityResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.jm.portfolio.domain.authority.domain.QAuthority.authority;

@Slf4j
@RequiredArgsConstructor
public class AuthorityRepositoryImpl implements AuthorityRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int authCount() {
        return Math.toIntExact(jpaQueryFactory
                .select(authority.count())
                .from(authority)
                .fetchFirst());
    }

    @Override
    public AuthorityListResponse getAuthList() {
        List<AuthorityResponse> content = jpaQueryFactory
                .select(Projections.fields(AuthorityResponse.class,
                        authority.createdAt,
                        authority.lastUpdatedAt,
                        authority.createdIp,
                        authority.lastUpdatedIp,
                        authority.authorityCode,
                        authority.authorityName,
                        authority.authorityDesc))
                .from(authority)
                .orderBy(authority.authorityCode.asc())
                .fetch();

        return new AuthorityListResponse(authCount(), content);
    }
}
