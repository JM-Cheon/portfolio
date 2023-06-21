package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.users.dao.UserRepository;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.request.UserDeletionRequest;
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
public class UserDeletionService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void deleteUserInfo(UserDeletionRequest deleteInfo) {

        Users userInfo = userRepository.findByEmail(deleteInfo.getEmail());
        if(userInfo == null || !passwordEncoder.matches(deleteInfo.getPassword(), userInfo.getPassword())) {
            throw new PasswordNotMatchException();
        }

        Users getUser = deleteInfo.toEntity();
        userRepository.deleteUserInfo(getUser);
    }
}