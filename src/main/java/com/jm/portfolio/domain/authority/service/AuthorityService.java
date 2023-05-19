package com.jm.portfolio.domain.authority.service;

import com.jm.portfolio.domain.authority.dao.AuthorityRepository;
import com.jm.portfolio.domain.authority.dto.request.AuthoritySaveRequest;
import com.jm.portfolio.domain.authority.dto.response.AuthorityListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public void saveAuth(AuthoritySaveRequest newAuth) {
        authorityRepository.save(newAuth.toEntity());
    }

    public AuthorityListResponse getAuthList() {

        return authorityRepository.getAuthList();
    }
}
