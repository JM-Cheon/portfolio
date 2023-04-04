package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.users.dto.UsersDTO;

import java.util.List;

public interface UsersRetrieveService {

    List<UsersDTO> getUserList(String sort);
}
