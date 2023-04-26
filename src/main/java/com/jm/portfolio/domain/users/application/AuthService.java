package com.jm.portfolio.domain.users.application;

import com.jm.portfolio.domain.users.dto.request.AuthSaveRequest;
import com.jm.portfolio.global.common.paging.dto.Criteria;
import com.jm.portfolio.global.common.paging.dto.response.PagingResponse;

import java.util.List;

public interface AuthService {
    void saveAuth(AuthSaveRequest newAuth);

    PagingResponse getAuthList(Criteria criteria);
}
