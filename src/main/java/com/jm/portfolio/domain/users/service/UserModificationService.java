package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.users.dao.UserRepository;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.request.PasswordModificationRequest;
import com.jm.portfolio.domain.users.dto.request.UserModificationRequest;
import com.jm.portfolio.domain.users.exception.PasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserModificationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void modifyUserInfo(UserModificationRequest modifyInfo) {

        if(modifyInfo.getNickname() != null) {
            userRepository.modifyNickname(modifyInfo);
        }
        if(modifyInfo.getName().getValue() != null) {
            userRepository.modifyName(modifyInfo);
        }
        if(modifyInfo.getBirth().getValue() != null) {
            userRepository.modifyBirth(modifyInfo);
        }
    }

    public void modifyPassword(PasswordModificationRequest modifyInfo) {

        Users userInfo = userRepository.findByEmail(modifyInfo.getEmail());
        if(userInfo == null || !passwordEncoder.matches(modifyInfo.getPassword(), userInfo.getPassword())) {
            throw new PasswordNotMatchException();
        }
        Users user = modifyInfo.toEntity();
        user.hashPassword(passwordEncoder);
        userRepository.modifyPassword(user);
    }
}