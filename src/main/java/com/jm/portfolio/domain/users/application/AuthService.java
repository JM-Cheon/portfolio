package com.jm.portfolio.domain.users.application;

import com.jm.portfolio.domain.users.dto.request.AuthSaveRequest;
import com.jm.portfolio.domain.users.dto.response.AuthResponse;
import com.jm.portfolio.global.common.paging.dto.Criteria;

import java.util.List;

public interface AuthService {
    void saveAuth(AuthSaveRequest newAuth);

    List<AuthResponse> getAuthList(Criteria criteria);

    int getAuthTotalCount();
}
