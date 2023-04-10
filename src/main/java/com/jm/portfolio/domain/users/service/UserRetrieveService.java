package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.users.dto.response.UserResponse;

import java.util.List;

public interface UserRetrieveService {

    List<UserResponse> getUserList(String sort);
}
