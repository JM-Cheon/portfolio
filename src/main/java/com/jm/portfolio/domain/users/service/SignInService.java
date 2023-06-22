package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.admin.dao.CountVisitorRepository;
import com.jm.portfolio.domain.admin.dao.SignInLogRepository;
import com.jm.portfolio.domain.admin.domain.SignInLog;
import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.request.SignInRequest;
import com.jm.portfolio.domain.users.exception.SigninFailedException;
import com.jm.portfolio.domain.users.dao.UserRepository;
import com.jm.portfolio.global.jwt.TokenProvider;
import com.jm.portfolio.global.jwt.dao.RefreshTokenRedisRepository;
import com.jm.portfolio.global.jwt.domain.RefreshToken;
import com.jm.portfolio.global.jwt.dto.response.TokenResponse;
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
    private final CountVisitorRepository countVisitorRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public TokenResponse signIn(SignInRequest user) {

        Users userInfo = userRepository.findByEmail(Email.of(user.getEmail()));
        if(userInfo == null || !passwordEncoder.matches(user.getPassword(), userInfo.getPassword())) {
            signInLogRepository.save(new SignInLog(Email.of(user.getEmail()), false));
            throw new SigninFailedException();
        }
        signInLogRepository.save(new SignInLog(Email.of(user.getEmail()), true));

        TokenResponse tokenResponse = tokenProvider.generatedToken(userInfo);
        if(refreshTokenRedisRepository.findById(user.getEmail()).isPresent()) {
            refreshTokenRedisRepository.deleteById(user.getEmail());
        }
        refreshTokenRedisRepository.save(RefreshToken.builder()
                .email(userInfo.getEmail().getValue())
                .refreshToken(tokenResponse.getRefreshToken())
                .expiresIn(tokenResponse.getRefreshTokenExpiresIn())
                .build());

        // TODO: 일일 방문자 수를 카테고리별 방문자 수 로 변경 (redis, scheduler 이용)
//        CountVisitorResponse todayVisitInfo = countVisitorRepository.getTodayVisitInfo();
//        if(todayVisitInfo == null) {
//            countVisitorRepository.save(new CountVisitor(LocalDate.now(), 1L));
//        } else {
//            countVisitorRepository.incrementVisit();
//        }

        return tokenResponse;
    }

    public TokenResponse reissueAccessToken(String refreshToken) {
        tokenProvider.validateRefreshToken(refreshToken);
        return tokenProvider.reissueAccessToken(refreshToken);
    }
}
