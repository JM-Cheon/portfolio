package com.jm.portfolio.domain.authority.dao;

import com.jm.portfolio.domain.authority.domain.UserRole;
import com.jm.portfolio.domain.authority.domain.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {

}
