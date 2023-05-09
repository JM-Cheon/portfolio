package com.jm.portfolio.domain.admin.service;

import com.jm.portfolio.domain.admin.dao.AuthRepository;
import com.jm.portfolio.domain.admin.domain.Authority;
import com.jm.portfolio.domain.admin.dto.request.AuthSaveRequest;
import com.jm.portfolio.domain.admin.dto.response.AuthListResponse;
import com.jm.portfolio.domain.admin.dto.response.AuthResponse;
import com.jm.portfolio.global.common.paging.PagingDTO;
import com.jm.portfolio.global.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public void saveAuth(AuthSaveRequest newAuth) {
        authRepository.save(newAuth.toEntity());
    }

    public AuthListResponse getAuthList() {

        return authRepository.getAuthList();
    }
}
