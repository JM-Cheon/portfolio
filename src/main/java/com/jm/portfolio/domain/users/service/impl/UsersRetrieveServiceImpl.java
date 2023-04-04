package com.jm.portfolio.domain.users.service.impl;

import com.jm.portfolio.domain.users.dao.UsersDAO;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.service.UsersRetrieveService;
import com.jm.portfolio.domain.users.dto.UsersDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersRetrieveServiceImpl implements UsersRetrieveService {

    private final UsersDAO usersDAO;

    @Override
    public List<UsersDTO> getUserList(String sort) {
        List<Users> userList = usersDAO.findAll(Sort.by(sort));
        return userList.stream().map(UsersDTO::new).collect(Collectors.toList());
    }
}