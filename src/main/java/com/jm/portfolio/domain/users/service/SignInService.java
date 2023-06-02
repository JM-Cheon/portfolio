package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.admin.dao.CountVisitorRepository;
import com.jm.portfolio.domain.admin.dao.SignInLogRepository;
import com.jm.portfolio.domain.admin.domain.CountVisitor;
import com.jm.portfolio.domain.admin.domain.SignInLog;
import com.jm.portfolio.domain.admin.dto.response.CountVisitorResponse;
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
import java.time.LocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SignInService {

    private final UserRepository userRepository;
    private final SignInLogRepository signInLogRepository;
    private final CountVisitorRepository countVisitorRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public TokenResponse signIn(SignInRequest user) {

        Users userInfo = userRepository.findByEmail(user.getEmail());
        if(userInfo == null || !passwordEncoder.matches(user.getPassword(), userInfo.getPassword())) {
            signInLogRepository.save(new SignInLog(user.getEmail(), false));
            throw new SigninFailedException();
        }
        signInLogRepository.save(new SignInLog(user.getEmail(), true));

        CountVisitorResponse todayVisitInfo = countVisitorRepository.getTodayVisitInfo();
        if(todayVisitInfo == null) {
            countVisitorRepository.save(new CountVisitor(LocalDate.now(), 1L));
        } else {
            countVisitorRepository.incrementVisit();
        }

        return tokenProvider.generatedToken(userInfo);
    }
}
