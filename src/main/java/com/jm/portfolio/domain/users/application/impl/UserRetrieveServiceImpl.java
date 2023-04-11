package com.jm.portfolio.domain.users.application.impl;

import com.jm.portfolio.domain.users.dao.UserDAO;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.application.UserRetrieveService;
import com.jm.portfolio.global.common.paging.PagingCriteria;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRetrieveServiceImpl implements UserRetrieveService {

    private final UserDAO userDAO;

    @Override
    public List<UserResponse> getUserList(PagingCriteria pagingCriteria) {
        int index = pagingCriteria.getPageNo() - 1;
        int count = pagingCriteria.getAmount();
//        String searchValue = pagingCriteria.getSearchValue();

        Pageable paging = PageRequest.of(index, count, Sort.by("idx"));

        Page<Users> result = userDAO.findAll(paging);
        List<Users> userList = result.getContent();
        List<UserResponse> userResponseList = userList.stream().map(UserResponse::new).collect(Collectors.toList());
        PagingResponse response = new PagingResponse();
        response.setData(userResponseList);
        response.setPageInfo(new PagingDTO(new PagingCriteria(1, 10), 1));
        return userResponseList;
    }
}