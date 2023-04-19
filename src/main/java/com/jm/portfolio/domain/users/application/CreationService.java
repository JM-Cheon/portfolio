package com.jm.portfolio.domain.users.application;

import com.jm.portfolio.domain.users.dto.request.SignupRequest;
import com.jm.portfolio.domain.users.dto.response.UserResponse;

public interface CreationService {
    UserResponse signup(SignupRequest newUser);
}
