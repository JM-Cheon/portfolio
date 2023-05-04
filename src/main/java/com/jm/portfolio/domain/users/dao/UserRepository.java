package com.jm.portfolio.domain.users.dao;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("SELECT MAX(u.idx) FROM Users u")
    int maxUserIdx();
    Page<Users> findByEmailContains(Pageable paging, String email);
    Page<Users> findByNicknameContains(Pageable paging, String nickname);
    Page<Users> findByCreatedIpContains(Pageable paging, String createdIp);
    Page<Users> findByLastUpdatedIpContains(Pageable paging, String lastUpdateIp);
    Page<Users> findByWithdrawIpContains(Pageable paging, String withdrawIp);
    Page<Users> findByIsWithdraw(Pageable paging, String isWithdraw);
    Page<Users> findByIsExpired(Pageable paging, String isExpired);
    Page<Users> findByIsDisabled(Pageable paging, String isDisabled);
    Page<Users> findByCreatedAtBetween(Pageable paging, LocalDateTime startDate, LocalDateTime endDate);
    Page<Users> findByCreatedAtBefore(Pageable paging, LocalDateTime endDate);
    Page<Users> findByCreatedAtAfter(Pageable paging, LocalDateTime startDate);
    Page<Users> findByWithdrawAtBetween(Pageable paging, LocalDateTime startDate, LocalDateTime endDate);
    Page<Users> findByWithdrawAtBefore(Pageable paging, LocalDateTime endDate);
    Page<Users> findByWithdrawAtAfter(Pageable paging, LocalDateTime startDate);
    Page<Users> findByLastUpdatedAtBetween(Pageable paging, LocalDateTime startDate, LocalDateTime endDate);
    Page<Users> findByLastUpdatedAtBefore(Pageable paging, LocalDateTime endDate);
    Page<Users> findByLastUpdatedAtAfter(Pageable paging, LocalDateTime startDate);
    boolean existsByEmail(Email email);
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    Users findByEmail(Email email);
}
