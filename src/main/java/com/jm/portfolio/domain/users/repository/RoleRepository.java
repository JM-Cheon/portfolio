package com.jm.portfolio.domain.users.repository;

import com.jm.portfolio.domain.users.domain.Role;
import com.jm.portfolio.domain.users.domain.RolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, RolePK> {

}
