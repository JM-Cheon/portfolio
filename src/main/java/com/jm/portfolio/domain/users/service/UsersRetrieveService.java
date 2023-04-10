package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.users.dto.response.UserSearchResponse;

import java.util.List;

public interface UsersRetrieveService {

    List<UserSearchResponse> getUserList(String sort);
}
