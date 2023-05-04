package com.jm.portfolio.domain.admin.service;

import com.jm.portfolio.domain.users.dao.UserRepository;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.global.common.paging.dto.Criteria;
import com.jm.portfolio.global.common.paging.dto.PagingDTO;
import com.jm.portfolio.global.common.paging.dto.response.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserManageService {

    private final UserRepository userRepository;

    public PagingResponse getUserList(Criteria criteria) {
        int index = criteria.getPageNo() - 1;
        int count = criteria.getAmount();
        String sortBy = criteria.getSortBy();
        String orderBy = criteria.getOrderBy();
        String searchBy = criteria.getSearchBy();
        String searchValue = criteria.getSearchValue();
        LocalDateTime startDate = criteria.getStartDate();
        LocalDateTime endDate = criteria.getEndDate();

        Pageable paging;
        if(orderBy.equals("asc")) {
            paging = PageRequest.of(index, count, Sort.by(sortBy).ascending());
        } else {
            paging = PageRequest.of(index, count, Sort.by(sortBy).descending());
        }

        Page<Users> result;
        if((searchValue == null || searchValue.isBlank() || searchValue.isEmpty()) && (startDate == null && endDate == null)) {
            result = userRepository.findAll(paging);
        } else {
            switch (searchBy) {
                case "email":
                    result = userRepository.findByEmailContains(paging, searchValue);
                    break;
                case "nickname":
                    result = userRepository.findByNicknameContains(paging, searchValue);
                    break;
                case "createdAt":
                    if(startDate == null) {
                        result = userRepository.findByCreatedAtBefore(paging, endDate);
                    } else if (endDate == null) {
                        result = userRepository.findByCreatedAtAfter(paging, startDate);
                    } else {
                        result = userRepository.findByCreatedAtBetween(paging, startDate, endDate);
                    }
                    break;
                case "lastUpdatedAt":
                    if(startDate == null) {
                        result = userRepository.findByLastUpdatedAtBefore(paging, endDate);
                    } else if (endDate == null) {
                        result = userRepository.findByLastUpdatedAtAfter(paging, startDate);
                    } else {
                        result = userRepository.findByLastUpdatedAtBetween(paging, startDate, endDate);
                    }
                    break;
                case "withdrawAt":
                    if(startDate == null) {
                        result = userRepository.findByWithdrawAtBefore(paging, endDate);
                    } else if (endDate == null) {
                        result = userRepository.findByWithdrawAtAfter(paging, startDate);
                    } else {
                        result = userRepository.findByWithdrawAtBetween(paging, startDate, endDate);
                    }
                    break;
                case "isWithdraw":
                    log.debug(searchValue);
                    result = userRepository.findByIsWithdraw(paging, searchValue);
                    break;
                case "isDisabled":
                    log.debug(searchValue);
                    result = userRepository.findByIsDisabled(paging, searchValue);
                    break;
                case "isExpired":
                    log.debug(searchValue);
                    result = userRepository.findByIsExpired(paging, searchValue);
                    break;
                case "withdrawIp":
                    result = userRepository.findByWithdrawIpContains(paging, searchValue);
                    break;
                case "createdIp":
                    result = userRepository.findByCreatedIpContains(paging, searchValue);
                    break;
                case "lastUpdatedIp":
                    result = userRepository.findByLastUpdatedIpContains(paging, searchValue);
                    break;
                default:
                    result = userRepository.findAll(paging);
                    break;
            }
        }

        PagingResponse response = new PagingResponse();
        response.setData(result.getContent().stream().map(UserResponse::new).collect(Collectors.toList()));
        response.setPageInfo(new PagingDTO(criteria, userRepository.maxUserIdx()));

        return response;
    }
}