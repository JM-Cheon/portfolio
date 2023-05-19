package com.jm.portfolio.domain.authority.dao;

import com.jm.portfolio.domain.authority.dto.response.AuthorityListResponse;

public interface AuthorityRepositoryCustom {

    int authCount();

    AuthorityListResponse getAuthList();
}
