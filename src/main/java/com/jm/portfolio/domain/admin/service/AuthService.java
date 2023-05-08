package com.jm.portfolio.domain.admin.service;

import com.jm.portfolio.domain.admin.dao.AuthRepository;
import com.jm.portfolio.domain.admin.domain.Authority;
import com.jm.portfolio.domain.admin.dto.request.AuthSaveRequest;
import com.jm.portfolio.domain.admin.dto.response.AuthResponse;
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

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public void saveAuth(AuthSaveRequest newAuth) {
        authRepository.save(newAuth.toEntity());
    }

    public PagingResponse getAuthList(SearchCondition searchCondition) {

        int index = searchCondition.getPageNo() - 1;
        int count = searchCondition.getAmount();
        String sortBy = searchCondition.getSortBy();
        String orderBy = searchCondition.getOrderBy();
        String searchBy = searchCondition.getSearchBy();
        String searchValue = searchCondition.getSearchValue();
        LocalDateTime startDate = searchCondition.getStartDate();
        LocalDateTime endDate = searchCondition.getEndDate();

        Pageable paging;
        if(orderBy.equals("asc")) {
            paging = PageRequest.of(index, count, Sort.by(sortBy).ascending());
        } else {
            paging = PageRequest.of(index, count, Sort.by(sortBy).descending());
        }

        Page<Authority> result;
        if((searchValue == null || searchValue.isBlank() || searchValue.isEmpty()) && (startDate == null && endDate == null)) {
            result = authRepository.findAll(paging);
        } else {
            switch (searchBy) {
                case "authCode":
                    result = authRepository.findByAuthCodeContains(paging, searchValue);
                    break;
                case "authName":
                    result = authRepository.findByAuthNameContains(paging, searchValue);
                    break;
                case "authDesc":
                    result = authRepository.findByAuthDescContains(paging, searchValue);
                    break;
                case "createdAt":
                    if(startDate == null) {
                        result = authRepository.findByCreatedAtBefore(paging, endDate);
                    } else if (endDate == null) {
                        result = authRepository.findByCreatedAtAfter(paging, startDate);
                    } else {
                        result = authRepository.findByCreatedAtBetween(paging, startDate, endDate);
                    }
                    break;
                case "lastUpdatedAt":
                    if(startDate == null) {
                        result = authRepository.findByLastUpdatedAtBefore(paging, endDate);
                    } else if (endDate == null) {
                        result = authRepository.findByLastUpdatedAtAfter(paging, startDate);
                    } else {
                        result = authRepository.findByLastUpdatedAtBetween(paging, startDate, endDate);
                    }
                    break;
                case "createdIp":
                    result = authRepository.findByCreatedIpContains(paging, searchValue);
                    break;
                case "lastUpdatedIp":
                    result = authRepository.findByLastUpdatedIpContains(paging, searchValue);
                    break;
                default:
                    result = authRepository.findAll(paging);
                    break;
            }
        }

        PagingResponse response = new PagingResponse();
        response.setData(result.getContent().stream().map(AuthResponse::new).collect(Collectors.toList()));
        response.setPageInfo(new PagingDTO(searchCondition, authRepository.countAuthCode()));

        return response;
    }
}
