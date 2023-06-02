package com.jm.portfolio.domain.admin.dao;

import com.jm.portfolio.domain.admin.domain.CountVisitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CountVisitorRepository extends JpaRepository<CountVisitor, LocalDate>, CountVisitorRepositoryCustom {
}
