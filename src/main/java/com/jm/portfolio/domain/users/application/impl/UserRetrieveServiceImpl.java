package com.jm.portfolio.domain.users.application.impl;

import com.jm.portfolio.domain.users.dao.UserDAO;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.application.UserRetrieveService;
import com.jm.portfolio.global.common.paging.Criteria;
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
    public List<UserResponse> getUserList(Criteria criteria) {
        int index = criteria.getPageNo() - 1;
        int count = criteria.getAmount();
        String sortBy = criteria.getSortBy();
        String orderBy = criteria.getOrderBy();
        String searchBy = criteria.getSearchBy();
        String searchValue = criteria.getSearchValue();

        Pageable paging;
        if(orderBy.equals("asc")) {
            paging = PageRequest.of(index, count, Sort.by(sortBy).ascending());
        } else {
            paging = PageRequest.of(index, count, Sort.by(sortBy).descending());
        }

        Page<Users> result;
        if(searchValue == null || searchValue.isBlank() || searchValue.isEmpty()) {
            result = userDAO.findAll(paging);
        } else {
            switch (searchBy) {
                case "email":
                    result = userDAO.findByEmailContains(paging, searchValue);
                    break;
                case "nickname":
                    result = userDAO.findByNicknameContains(paging, searchValue);
                    break;
                case "createdAt":
                    if(criteria.getStartDate() == null) {
                        result = userDAO.findByCreatedAtAfter(paging, criteria.getEndDate());
                    } else if (criteria.getEndDate() == null) {
                        result = userDAO.findByCreatedAtBefore(paging, criteria.getStartDate());
                    } else {
                        result = userDAO.findByCreatedAtBetween(paging, criteria.getStartDate(), criteria.getEndDate());
                    }
                    break;
                case "lastUpdatedAt":
                    if(criteria.getStartDate() == null) {
                        result = userDAO.findByLastUpdatedAtAfter(paging, criteria.getEndDate());
                    } else if (criteria.getEndDate() == null) {
                        result = userDAO.findByLastUpdatedAtBefore(paging, criteria.getStartDate());
                    } else {
                        result = userDAO.findByLastUpdatedAtBetween(paging, criteria.getStartDate(), criteria.getEndDate());
                    }
                    break;
                case "withdrawAt":
                    if(criteria.getStartDate() == null) {
                        result = userDAO.findByWithdrawAtAfter(paging, criteria.getEndDate());
                    } else if (criteria.getEndDate() == null) {
                        result = userDAO.findByWithdrawAtBefore(paging, criteria.getStartDate());
                    } else {
                        result = userDAO.findByWithdrawAtBetween(paging, criteria.getStartDate(), criteria.getEndDate());
                    }
                    break;
                case "isWithdraw":
                    log.debug(searchValue);
                    result = userDAO.findByIsWithdraw(paging, searchValue);
                    break;
                case "isDisabled":
                    log.debug(searchValue);
                    result = userDAO.findByIsDisabled(paging, searchValue);
                    break;
                case "isExpired":
                    log.debug(searchValue);
                    result = userDAO.findByIsExpired(paging, searchValue);
                    break;
                case "withdrawIp":
                    result = userDAO.findByWithdrawIpContains(paging, searchValue);
                    break;
                case "createdIp":
                    result = userDAO.findByCreatedIpContains(paging, searchValue);
                    break;
                case "lastUpdatedIp":
                    result = userDAO.findByLastUpdatedIpContains(paging, searchValue);
                    break;
                default:
                    result = userDAO.findAll(paging);
                    break;
            }
        }

        List<Users> userList = result.getContent();

        return userList.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @Override
    public int getUserTotalCount() {
        return userDAO.findAll().size();
    }
}