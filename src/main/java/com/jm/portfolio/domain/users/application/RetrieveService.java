package com.jm.portfolio.domain.users.application;

import com.jm.portfolio.domain.users.dto.response.RetrieveResponse;
import com.jm.portfolio.global.common.paging.dto.Criteria;

import java.util.List;

public interface RetrieveService {

    List<RetrieveResponse> getUserList(Criteria criteria);

    int getUserTotalCount();
}
