package com.jm.portfolio.domain.users.application.impl;

import com.jm.portfolio.domain.users.application.SigninService;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.request.SigninRequest;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.exception.SigninFailedException;
import com.jm.portfolio.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SigninServiceImpl implements SigninService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse signin(SigninRequest user) {
        Users getUser = user.toEntity();
        Users userInfo = userRepository.findByEmail(getUser.getEmail());
        if(userInfo == null || !passwordEncoder.matches(user.getPassword(), userInfo.getPassword())) {
            throw new SigninFailedException();
        }
        return new UserResponse(userInfo);
    }
}
