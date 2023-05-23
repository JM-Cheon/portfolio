package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.exception.UserNotFoundException;
import com.jm.portfolio.domain.users.dao.UserRepository;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.global.error.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserRetrieveService{

    private final UserRepository userRepository;

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