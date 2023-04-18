package com.jm.portfolio.domain.users.application;

import com.jm.portfolio.domain.users.dto.request.SignupRequest;

public interface CreationService {
    void signup(SignupRequest newUser);
}
