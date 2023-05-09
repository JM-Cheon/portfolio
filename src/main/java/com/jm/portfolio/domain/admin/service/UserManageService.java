package com.jm.portfolio.domain.admin.service;

import com.jm.portfolio.domain.admin.dao.SignInLogRepository;
import com.jm.portfolio.domain.admin.dto.response.SignInLogResponse;
import com.jm.portfolio.domain.users.dao.UserRepository;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.global.common.paging.SearchCondition;
import com.jm.portfolio.global.common.paging.PagingDTO;
import com.jm.portfolio.global.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserManageService {

    private final UserRepository userRepository;
    private final SignInLogRepository signInLogRepository;

    public PagingResponse getUserList(SearchCondition searchCondition) {
        int index = searchCondition.getPageNo() - 1;
        int count = searchCondition.getAmount();
        String sortBy = searchCondition.getSortBy();
        String orderBy = searchCondition.getOrderBy();

        Pageable page;
        if(orderBy.equals("asc")) {
            page = PageRequest.of(index, count, Sort.by(sortBy).ascending());
        } else {
            page = PageRequest.of(index, count, Sort.by(sortBy).descending());
        }

        Page<UserResponse> result = userRepository.getUserList(page, searchCondition);

        PagingResponse response = new PagingResponse();
        response.setData(result.getContent());
        response.setPageInfo(new PagingDTO(searchCondition, (int) result.getTotalElements()));

        return response;
    }

    public PagingResponse getSignInLog(SearchCondition searchCondition) {
        int index = searchCondition.getPageNo() - 1;
        int count = searchCondition.getAmount();
        String sortBy = searchCondition.getSortBy();
        String orderBy = searchCondition.getOrderBy();

        Pageable page;
        if(orderBy.equals("asc")) {
            page = PageRequest.of(index, count, Sort.by(sortBy).ascending());
        } else {
            page = PageRequest.of(index, count, Sort.by(sortBy).descending());
        }

        Page<SignInLogResponse> result = signInLogRepository.getSignInLogList(page, searchCondition);

        PagingResponse response = new PagingResponse();
        response.setData(result.getContent());
        response.setPageInfo(new PagingDTO(searchCondition, (int) result.getTotalElements()));

        return response;
    }
}