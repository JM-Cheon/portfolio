package com.jm.portfolio.domain.admin.application;

import com.jm.portfolio.domain.admin.dto.request.AuthSaveRequest;
import com.jm.portfolio.global.common.paging.dto.Criteria;
import com.jm.portfolio.global.common.paging.dto.response.PagingResponse;

public interface AuthService {
    void saveAuth(AuthSaveRequest newAuth);

    PagingResponse getAuthList(Criteria criteria);
}
