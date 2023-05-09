package com.jm.portfolio.domain.admin.dao;

import com.jm.portfolio.domain.admin.dto.response.AuthListResponse;

public interface AuthRepositoryCustom {

    int authCount();

    AuthListResponse getAuthList();
}
