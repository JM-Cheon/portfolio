package com.jm.portfolio.domain.users.application;

import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.global.common.paging.dto.Criteria;

import java.util.List;

public interface UserRetrieveService {

    List<UserResponse> getUserList(Criteria criteria);

    int getUserTotalCount();
}
