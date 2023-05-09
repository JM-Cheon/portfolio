package com.jm.portfolio.domain.admin.dao;

import com.jm.portfolio.domain.admin.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Authority, String> , AuthRepositoryCustom {

}
