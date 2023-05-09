package com.jm.portfolio.domain.users.service.impl;

import com.jm.portfolio.domain.model.AuthEnum;
import com.jm.portfolio.domain.users.dao.RoleRepository;
import com.jm.portfolio.domain.users.domain.Role;
import com.jm.portfolio.domain.users.dto.request.SignupRequest;
import com.jm.portfolio.domain.users.service.SignUpService;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dao.UserRepository;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.exception.EmailDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse signUp(SignupRequest newUser) {

        if(userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailDuplicateException(newUser.getEmail());
        }

        Users user = newUser.toEntity();
        user.hashPassword(passwordEncoder);
        userRepository.save(user);

        long maxIdx = userRepository.maxUserIdx();
        Role role = new Role(newUser.getCreatedIp(), newUser.getLastUpdatedIp(), maxIdx, AuthEnum.USER);
        roleRepository.save(role);

        return new UserResponse(user);
    }
}
