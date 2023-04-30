package com.jm.portfolio.domain.users.application;

import com.jm.portfolio.domain.users.dto.request.SigninRequest;
import com.jm.portfolio.domain.users.dto.response.UserResponse;

public interface SigninService {
    UserResponse signin(SigninRequest user);
}
