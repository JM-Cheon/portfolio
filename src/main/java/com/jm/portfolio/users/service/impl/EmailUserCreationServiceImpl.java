package com.jm.portfolio.users.service.impl;

import com.jm.portfolio.users.dto.UsersDTO;
import com.jm.portfolio.users.entity.Users;
import com.jm.portfolio.users.repository.UserRepository;
import com.jm.portfolio.users.service.UserCreationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailUserCreationServiceImpl implements UserCreationService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void signup(UsersDTO newUser) {

        Users user = newUser.toEntity();
        userRepository.save(user);
    }
}
