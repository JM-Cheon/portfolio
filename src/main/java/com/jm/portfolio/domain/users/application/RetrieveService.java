package com.jm.portfolio.domain.users.application;

import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.global.common.paging.dto.Criteria;
import com.jm.portfolio.global.common.paging.dto.response.PagingResponse;

import java.util.List;

public interface RetrieveService {

    UserResponse myInfo(String email);
}
