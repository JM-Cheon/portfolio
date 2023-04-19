package com.jm.portfolio.domain.users.domain;

import com.jm.portfolio.global.common.base.domain.BaseDomain;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "authority")
@EqualsAndHashCode(of = {"authCode"}, callSuper = false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority extends BaseDomain {

    @Id
    @Column(name = "auth_code", updatable = false, nullable = false)
    private String authCode;

    @Column(name = "auth_name", nullable = false, unique = true)
    private String authName;

    @Column(name = "auth_desc", nullable = false)
    private String authDesc;

    @Builder
    public Authority (LocalDateTime createdAt, LocalDateTime lastUpdatedAt, String createdIp, String lastUpdatedIp, String authCode, String authName, String authDesc) {
        super(createdAt, lastUpdatedAt, createdIp, lastUpdatedIp);
        this.authCode = authCode;
        this.authName = authName;
        this.authDesc = authDesc;
    }
}
