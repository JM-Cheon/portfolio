package com.jm.portfolio.domain.authority.dao;

import com.jm.portfolio.domain.authority.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String>, AuthorityRepositoryCustom {

}
