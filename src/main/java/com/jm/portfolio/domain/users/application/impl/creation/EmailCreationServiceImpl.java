package com.jm.portfolio.domain.users.application.impl.creation;

import com.jm.portfolio.domain.users.repository.RoleRepository;
import com.jm.portfolio.domain.users.domain.Role;
import com.jm.portfolio.domain.users.dto.request.SignupRequest;
import com.jm.portfolio.domain.users.application.CreationService;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.repository.UserRepository;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.exception.EmailDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailCreationServiceImpl implements CreationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponse signup(SignupRequest newUser) {

        if(userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailDuplicateException(newUser.getEmail());
        }

        Users user = newUser.toEntity();
        //TODO: 비밀번호 암호화
        userRepository.save(user);

        int maxIdx = userRepository.maxUserIdx();
        Role role = new Role(newUser.getCreatedIp(), newUser.getLastUpdatedIp(), maxIdx, "USER");
        roleRepository.save(role);

        return new UserResponse(user);
    }
}
