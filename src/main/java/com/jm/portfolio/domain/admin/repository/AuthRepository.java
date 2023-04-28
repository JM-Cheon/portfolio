package com.jm.portfolio.domain.admin.repository;

import com.jm.portfolio.domain.admin.domain.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AuthRepository extends JpaRepository<Authority, String> {

    @Query("SELECT COUNT(a.authCode) FROM Authority a")
    int countAuthCode();

    Page<Authority> findByAuthCodeContains(Pageable paging, String authCode);

    Page<Authority> findByAuthNameContains(Pageable paging, String authName);

    Page<Authority> findByAuthDescContains(Pageable paging, String authDesc);

    Page<Authority> findByCreatedAtBefore(Pageable paging, LocalDateTime endDate);

    Page<Authority> findByCreatedAtAfter(Pageable paging, LocalDateTime startDate);

    Page<Authority> findByCreatedAtBetween(Pageable paging, LocalDateTime startDate, LocalDateTime endDate);

    Page<Authority> findByLastUpdatedAtBefore(Pageable paging, LocalDateTime endDate);

    Page<Authority> findByLastUpdatedAtAfter(Pageable paging, LocalDateTime startDate);

    Page<Authority> findByLastUpdatedAtBetween(Pageable paging, LocalDateTime startDate, LocalDateTime endDate);

    Page<Authority> findByCreatedIpContains(Pageable paging, String createdIp);

    Page<Authority> findByLastUpdatedIpContains(Pageable paging, String lastUpdatedIp);
}
