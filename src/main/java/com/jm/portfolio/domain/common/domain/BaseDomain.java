package com.jm.portfolio.domain.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public abstract class BaseDomain {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastUpdatedAt;

    @NotEmpty
    @Column(nullable = false, updatable = false)
    private String createdIp;

    @NotEmpty
    @Column(nullable = false)
    private String lastUpdatedIp;

    public BaseDomain(String createdIp, String lastUpdatedIp) {
        this.createdIp = createdIp;
        this.lastUpdatedIp = lastUpdatedIp;
    }
}
