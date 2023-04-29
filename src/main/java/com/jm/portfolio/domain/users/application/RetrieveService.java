package com.jm.portfolio.domain.users.application;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.dto.response.UserResponse;

public interface RetrieveService {

    UserResponse myInfo(Email email);
}
