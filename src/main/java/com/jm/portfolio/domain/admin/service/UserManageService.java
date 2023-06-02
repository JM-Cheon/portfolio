package com.jm.portfolio.domain.admin.service;

import com.jm.portfolio.domain.admin.dao.CountVisitorRepository;
import com.jm.portfolio.domain.admin.dao.SignInLogRepository;
import com.jm.portfolio.domain.admin.domain.CountVisitor;
import com.jm.portfolio.domain.admin.dto.response.CountVisitorResponse;
import com.jm.portfolio.domain.admin.dto.response.SignInLogResponse;
import com.jm.portfolio.domain.admin.dto.response.StatisticsResponse;
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

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserManageService {

    private final UserRepository userRepository;
    private final SignInLogRepository signInLogRepository;
    private final CountVisitorRepository countVisitorRepository;

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

    public StatisticsResponse getStatistics() {

        CountVisitorResponse todayVisitInfo = countVisitorRepository.getTodayVisitInfo();
        if(todayVisitInfo == null) {
            countVisitorRepository.save(new CountVisitor(LocalDate.now(), 0L));
        }
        Long todayVisit = countVisitorRepository.getTodayVisitInfo().getTotalVisit();
        Long totalUser = userRepository.countUser();
        Long monthVisit = countVisitorRepository.monthVisit();
        List<CountVisitorResponse> monthVisitList = countVisitorRepository.monthVisitList();

        return new StatisticsResponse(totalUser, todayVisit, monthVisit, monthVisitList);
    }
}