package com.jm.portfolio.domain.users.application.impl;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.exception.UserNotFoundException;
import com.jm.portfolio.domain.users.repository.UserRepository;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.application.RetrieveService;
import com.jm.portfolio.global.error.exception.ErrorCode;
import com.jm.portfolio.global.error.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetrieveServiceImpl implements RetrieveService {

    private final UserRepository userRepository;

    @Override
    public UserResponse myInfo(Email email) {
        if(email.getValue() == null || email.getValue().isBlank() || email.getValue().isEmpty()) {
            throw new InvalidValueException("Email is empty");
        }
        Users user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UserNotFoundException(email.getValue());
        }
        return new UserResponse(user);
    }
}