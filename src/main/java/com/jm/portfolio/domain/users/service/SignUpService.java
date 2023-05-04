package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.users.dto.request.SignupRequest;
import com.jm.portfolio.domain.users.dto.response.UserResponse;

public interface SignUpService {
    UserResponse signUp(SignupRequest newUser);
}
