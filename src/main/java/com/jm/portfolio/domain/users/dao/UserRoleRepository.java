package com.jm.portfolio.domain.users.dao;

import com.jm.portfolio.domain.users.domain.UserRole;
import com.jm.portfolio.domain.users.domain.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {

}
