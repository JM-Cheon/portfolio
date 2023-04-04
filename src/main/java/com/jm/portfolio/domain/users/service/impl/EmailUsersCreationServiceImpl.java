package com.jm.portfolio.domain.users.service.impl;

import com.jm.portfolio.domain.users.service.UsersCreationService;
import com.jm.portfolio.domain.users.dto.UsersDTO;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dao.UsersDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailUsersCreationServiceImpl implements UsersCreationService {

    private final UsersDAO usersDAO;

    @Override
    @Transactional
    public void signup(UsersDTO newUser) {

        Users user = newUser.toEntity();
        usersDAO.save(user);
    }
}
