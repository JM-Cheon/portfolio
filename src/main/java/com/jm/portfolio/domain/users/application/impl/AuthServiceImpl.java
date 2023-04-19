package com.jm.portfolio.domain.users.application.impl;

import com.jm.portfolio.domain.users.application.AuthService;
import com.jm.portfolio.domain.users.dao.AuthorityDAO;
import com.jm.portfolio.domain.users.domain.Authority;
import com.jm.portfolio.domain.users.dto.request.AuthSaveRequest;
import com.jm.portfolio.domain.users.dto.response.AuthResponse;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthorityDAO authorityDAO;

    @Override
    public void saveAuth(AuthSaveRequest newAuth) {
        authorityDAO.save(newAuth.toEntity());
    }

    @Override
    public List<AuthResponse> getAuthList() {
        List<Authority> getAuthList = authorityDAO.findAll();
        List<AuthResponse> authList = getAuthList.stream().map(AuthResponse::new).collect(Collectors.toList());
        return authList;
    }
}
