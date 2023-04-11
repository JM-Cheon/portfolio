package com.jm.portfolio.domain.users.application;

import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.global.common.paging.PagingCriteria;

import java.util.List;

public interface UserRetrieveService {

    List<UserResponse> getUserList(PagingCriteria pagingCriteria);
}
