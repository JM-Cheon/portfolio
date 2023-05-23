package com.jm.portfolio.global.common.base.domain;

import com.jm.portfolio.global.util.IpUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;

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
    @Column(updatable = false)
    private String createdIp;

    @NotEmpty
    @Column
    private String lastUpdatedIp;

    @PrePersist
    public void ipSet() {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String ip = null;

        if(servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            ip = IpUtil.getClientIp(request);
        }

        this.createdIp = this.createdIp == null ? ip : this.createdIp;
        this.lastUpdatedIp = this.lastUpdatedIp == null ? ip : this.lastUpdatedIp;
    }

    public BaseDomain (String createdIp, String lastUpdatedIp) {
        this.createdIp = createdIp;
        this.lastUpdatedIp = lastUpdatedIp;
    }
}
