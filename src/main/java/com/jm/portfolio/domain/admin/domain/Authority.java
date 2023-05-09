package com.jm.portfolio.domain.admin.domain;

import com.jm.portfolio.global.common.base.domain.BaseDomain;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authority")
@EqualsAndHashCode(of = {"authCode"}, callSuper = false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority extends BaseDomain {

    @Id
    @Column(name = "auth_code", nullable = false)
    private String authCode;

    @Column(name = "auth_name", nullable = false, unique = true)
    private String authName;

    @Column(name = "auth_desc", nullable = false)
    private String authDesc;

    @Builder
    public Authority (String createdIp, String lastUpdatedIp, String authCode, String authName, String authDesc) {
        super(createdIp, lastUpdatedIp);
        this.authCode = authCode;
        this.authName = authName;
        this.authDesc = authDesc;
    }
}
