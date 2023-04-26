package com.jm.portfolio.domain.users.application.impl;

import com.jm.portfolio.domain.users.application.AuthService;
import com.jm.portfolio.domain.users.dao.AuthorityDAO;
import com.jm.portfolio.domain.users.domain.Authority;
import com.jm.portfolio.domain.users.dto.request.AuthSaveRequest;
import com.jm.portfolio.domain.users.dto.response.AuthResponse;
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
    public PagingResponse getAuthList(Criteria criteria) {

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

        Page<Authority> result;
        if((searchValue == null || searchValue.isBlank() || searchValue.isEmpty()) && (startDate == null && endDate == null)) {
            result = authorityDAO.findAll(paging);
        } else {
            switch (searchBy) {
                case "authCode":
                    result = authorityDAO.findByAuthCodeContains(paging, searchValue);
                    break;
                case "authName":
                    result = authorityDAO.findByAuthNameContains(paging, searchValue);
                    break;
                case "authDesc":
                    result = authorityDAO.findByAuthDescContains(paging, searchValue);
                    break;
                case "createdAt":
                    if(startDate == null) {
                        result = authorityDAO.findByCreatedAtBefore(paging, endDate);
                    } else if (endDate == null) {
                        result = authorityDAO.findByCreatedAtAfter(paging, startDate);
                    } else {
                        result = authorityDAO.findByCreatedAtBetween(paging, startDate, endDate);
                    }
                    break;
                case "lastUpdatedAt":
                    if(startDate == null) {
                        result = authorityDAO.findByLastUpdatedAtBefore(paging, endDate);
                    } else if (endDate == null) {
                        result = authorityDAO.findByLastUpdatedAtAfter(paging, startDate);
                    } else {
                        result = authorityDAO.findByLastUpdatedAtBetween(paging, startDate, endDate);
                    }
                    break;
                case "createdIp":
                    result = authorityDAO.findByCreatedIpContains(paging, searchValue);
                    break;
                case "lastUpdatedIp":
                    result = authorityDAO.findByLastUpdatedIpContains(paging, searchValue);
                    break;
                default:
                    result = authorityDAO.findAll(paging);
                    break;
            }
        }

        PagingResponse response = new PagingResponse();
        response.setData(result.getContent().stream().map(AuthResponse::new).collect(Collectors.toList()));
        response.setPageInfo(new PagingDTO(criteria, authorityDAO.countAuthCode()));

        return response;
    }
}
