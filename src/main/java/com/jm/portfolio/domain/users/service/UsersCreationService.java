package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.users.dto.request.UserSignupRequest;

public interface UsersCreationService {
    void signup(UserSignupRequest newUser);
}
