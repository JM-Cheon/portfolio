package com.jm.portfolio.domain.admin.dao;

import com.jm.portfolio.domain.admin.dto.response.SignInLogResponse;
import com.jm.portfolio.global.common.paging.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SignInLogRepositoryCustom {

    Long maxSignInLogIdx();

    Page<SignInLogResponse> getSignInLogList(Pageable page, SearchCondition searchCondition);
}
