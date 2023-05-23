package com.jm.portfolio.domain.authority.domain;

import com.jm.portfolio.domain.model.AuthorityEnum;
import com.jm.portfolio.global.common.base.domain.BaseDomain;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authority")
@EqualsAndHashCode(of = {"authorityCode"}, callSuper = false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority extends BaseDomain {

    @Id
    @Column(name = "authority_code", nullable = false)
    private String authorityCode;

    @Column(name = "authority_name", nullable = false, unique = true)
    private String authorityName;

    @Column(name = "authority_desc", nullable = false)
    private String authorityDesc;

    @Builder
    public Authority(String createdIp, String lastUpdatedIp, String authorityCode, String authorityName, String authorityDesc) {
        super(createdIp, lastUpdatedIp);
        this.authorityCode = authorityCode;
        this.authorityName = authorityName;
        this.authorityDesc = authorityDesc;
    }

    @Builder
    public Authority(String authorityCode, String authorityName, String authorityDesc) {
        super();
        this.authorityCode = authorityCode;
        this.authorityName = authorityName;
        this.authorityDesc = authorityDesc;
    }
}
