package com.jm.portfolio.domain.users.application.impl.creation;

import com.jm.portfolio.domain.users.dao.RoleDAO;
import com.jm.portfolio.domain.users.domain.Role;
import com.jm.portfolio.domain.users.dto.request.SignupRequest;
import com.jm.portfolio.domain.users.application.CreationService;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dao.UserDAO;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.exception.EmailDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailCreationServiceImpl implements CreationService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Override
    @Transactional
    public UserResponse signup(SignupRequest newUser) {

        if(userDAO.existsByEmail(newUser.getEmail())) {
            throw new EmailDuplicateException(newUser.getEmail());
        }

        Users user = newUser.toEntity();
        //TODO: 비밀번호 암호화
        userDAO.save(user);

        int maxIdx = userDAO.maxUserIdx();
        Role role = new Role(newUser.getCreatedIp(), newUser.getLastUpdatedIp(), maxIdx, "USER");
        roleDAO.save(role);

        return new UserResponse(user);
    }
}
