package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.admin.dao.SignInLogRepository;
import com.jm.portfolio.domain.admin.domain.SignInLog;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.request.SigninRequest;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.exception.SigninFailedException;
import com.jm.portfolio.domain.users.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignInService {

    private final UserRepository userRepository;
    private final SignInLogRepository signInLogRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse signIn(SigninRequest user) {
        Users getUser = user.toEntity();
        Users userInfo = userRepository.findByEmail(getUser.getEmail());
        if(userInfo == null || !passwordEncoder.matches(user.getPassword(), userInfo.getPassword())) {
            throw new SigninFailedException();
        }

        SignInLog signInLog = new SignInLog(userInfo.getEmail(), user.getSignInIp());
        signInLogRepository.save(signInLog);

        return new UserResponse(userInfo);
    }
}
