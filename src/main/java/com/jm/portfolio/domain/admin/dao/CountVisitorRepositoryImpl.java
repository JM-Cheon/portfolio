package com.jm.portfolio.domain.admin.dao;

import com.jm.portfolio.domain.admin.domain.QCountVisitor;
import com.jm.portfolio.domain.admin.dto.response.CountVisitorResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

import static com.jm.portfolio.domain.admin.domain.QCountVisitor.countVisitor;

@Slf4j
@RequiredArgsConstructor
public class CountVisitorRepositoryImpl implements CountVisitorRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CountVisitorResponse getTodayVisitInfo() {
        return jpaQueryFactory
                .select(Projections.fields(CountVisitorResponse.class,
                        countVisitor.visitDate,
                        countVisitor.totalVisit))
                .from(countVisitor)
                .where(countVisitor.visitDate.eq(LocalDate.now()))
                .fetchOne();
    }

    @Override
    public Long monthVisit() {
        return jpaQueryFactory
                .select(countVisitor.totalVisit.sum())
                .from(countVisitor)
                .where(countVisitor.visitDate.between(LocalDate.now().withDayOfMonth(1), LocalDate.now().withDayOfMonth(31)))
                .fetchOne();
    }

    @Override
    public List<CountVisitorResponse> monthVisitList() {
        return jpaQueryFactory
                .select(Projections.fields(CountVisitorResponse.class,
                        countVisitor.visitDate,
                        countVisitor.totalVisit))
                .from(countVisitor)
                .where(countVisitor.visitDate.between(LocalDate.now().withDayOfMonth(1), LocalDate.now().withDayOfMonth(31)))
                .orderBy(countVisitor.visitDate.asc())
                .fetch();
    }

    @Override
    public void incrementVisit() {
        jpaQueryFactory
                .update(countVisitor)
                .set(countVisitor.totalVisit, countVisitor.totalVisit.add(1))
                .where(countVisitor.visitDate.eq(LocalDate.now()))
                .execute();
    }
}
