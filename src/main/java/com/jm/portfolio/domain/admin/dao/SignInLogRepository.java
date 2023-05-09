package com.jm.portfolio.domain.admin.dao;

import com.jm.portfolio.domain.admin.domain.SignInLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignInLogRepository extends JpaRepository<SignInLog, Long>, SignInLogRepositoryCustom {
}
