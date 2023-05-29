package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.admin.dao.SignInLogRepository;
import com.jm.portfolio.domain.admin.domain.SignInLog;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.request.SignInRequest;
import com.jm.portfolio.domain.users.exception.SigninFailedException;
import com.jm.portfolio.domain.users.dao.UserRepository;
import com.jm.portfolio.global.jwt.TokenProvider;
import com.jm.portfolio.global.jwt.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SignInService {

    private final UserRepository userRepository;
    private final SignInLogRepository signInLogRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public TokenResponse signIn(SignInRequest user) {
        Users getUser = user.toEntity();
        Users userInfo = userRepository.findByEmail(getUser.getEmail());
        if(userInfo == null || !passwordEncoder.matches(user.getPassword(), userInfo.getPassword())) {
            SignInLog signInLog = new SignInLog(getUser.getEmail(), false);
            signInLogRepository.save(signInLog);
            throw new SigninFailedException();
        }

        SignInLog signInLog = new SignInLog(getUser.getEmail(), true);
        signInLogRepository.save(signInLog);

        return tokenProvider.generatedToken(userInfo);
    }
}
