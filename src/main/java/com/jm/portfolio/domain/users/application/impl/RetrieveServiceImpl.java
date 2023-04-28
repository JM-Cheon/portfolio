package com.jm.portfolio.domain.users.application.impl;

import com.jm.portfolio.domain.users.repository.UserRepository;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.application.RetrieveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetrieveServiceImpl implements RetrieveService {

    private final UserRepository userRepository;

    @Override
    public UserResponse myInfo(String email) {
        if(email == null || email.isBlank() || email.isEmpty()) {

        }
        Users user = userRepository.findByIdx(userIdx);
        return new UserResponse(user);
    }
}