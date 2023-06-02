package com.jm.portfolio.domain.admin.dao;

import com.jm.portfolio.domain.admin.dto.response.CountVisitorResponse;

import java.util.List;

public interface CountVisitorRepositoryCustom {

    CountVisitorResponse getTodayVisitInfo();

    Long monthVisit();

    List<CountVisitorResponse> monthVisitList();

    void incrementVisit();
}
