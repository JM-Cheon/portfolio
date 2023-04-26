package com.jm.portfolio.global.common.base.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseDomain {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastUpdatedAt;

    @NotEmpty
    @Column(nullable = false, updatable = false)
    private String createdIp;

    @NotEmpty
    @Column(nullable = false)
    private String lastUpdatedIp;

    public BaseDomain(String createdIp, String lastUpdatedIp) {
//        this.createdAt = createdAt;
//        this.lastUpdatedAt = lastUpdatedAt;
        this.createdIp = createdIp;
        this.lastUpdatedIp = lastUpdatedIp;
    }
}
