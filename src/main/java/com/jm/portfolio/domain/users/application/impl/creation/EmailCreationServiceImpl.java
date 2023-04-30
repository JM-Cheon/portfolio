package com.jm.portfolio.domain.users.application.impl.creation;

import com.jm.portfolio.domain.users.repository.RoleRepository;
import com.jm.portfolio.domain.users.domain.Role;
import com.jm.portfolio.domain.users.dto.request.SignupRequest;
import com.jm.portfolio.domain.users.application.CreationService;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.repository.UserRepository;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.exception.EmailDuplicateException;
import com.jm.portfolio.global.error.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailCreationServiceImpl implements CreationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse signup(SignupRequest newUser) {

        if(userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailDuplicateException(newUser.getEmail());
        }

        Users user = newUser.toEntity();
        log.debug(user.toString());
        user.hashPassword(passwordEncoder);
        log.debug(user.toString());
        userRepository.save(user);

        int maxIdx = userRepository.maxUserIdx();
        Role role = new Role(newUser.getCreatedIp(), newUser.getLastUpdatedIp(), maxIdx, "USER");
        roleRepository.save(role);

        return new UserResponse(user);
    }
}
