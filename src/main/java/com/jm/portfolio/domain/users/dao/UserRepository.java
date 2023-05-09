package com.jm.portfolio.domain.users.dao;

import com.jm.portfolio.domain.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>, UserRepositoryCustom {

}
