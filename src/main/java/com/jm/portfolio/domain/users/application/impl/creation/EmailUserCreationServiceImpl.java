package com.jm.portfolio.domain.users.application.impl.creation;

import com.jm.portfolio.domain.users.dto.request.UserSignupRequest;
import com.jm.portfolio.domain.users.application.UserCreationService;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dao.UserDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailUserCreationServiceImpl implements UserCreationService {

    private final UserDAO userDAO;

    @Override
    @Transactional
    public void signup(UserSignupRequest newUser) {

        Users user = newUser.toEntity();
        userDAO.save(user);
    }
}
