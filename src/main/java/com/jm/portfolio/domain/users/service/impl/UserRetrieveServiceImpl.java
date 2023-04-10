package com.jm.portfolio.domain.users.service.impl;

import com.jm.portfolio.domain.users.dao.UserDAO;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.service.UserRetrieveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRetrieveServiceImpl implements UserRetrieveService {

    private final UserDAO userDAO;

    @Override
    public List<UserResponse> getUserList(String sort) {
        List<Users> userList = userDAO.findAll(Sort.by(sort));
        return userList.stream().map(UserResponse::new).collect(Collectors.toList());
    }
}