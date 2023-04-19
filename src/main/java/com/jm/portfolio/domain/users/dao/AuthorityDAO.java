package com.jm.portfolio.domain.users.dao;

import com.jm.portfolio.domain.users.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDAO extends JpaRepository<Authority, String> {
}
