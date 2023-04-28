package com.jm.portfolio.domain.admin.application;

import com.jm.portfolio.global.common.paging.dto.Criteria;
import com.jm.portfolio.global.common.paging.dto.response.PagingResponse;

public interface RetrieveService {

    PagingResponse getUserList(Criteria criteria);
}
