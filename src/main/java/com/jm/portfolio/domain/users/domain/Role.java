package com.jm.portfolio.domain.users.domain;

import com.jm.portfolio.domain.admin.domain.Authority;
import com.jm.portfolio.domain.model.AuthEnum;
import com.jm.portfolio.global.common.base.domain.BaseDomain;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role")
@IdClass(RolePK.class)
@EqualsAndHashCode(of = {"userIdx", "authCode"}, callSuper = false)
@Getter
@NoArgsConstructor
public class Role extends BaseDomain {

    @Id
    @Column(name = "user_idx", updatable = false, nullable = false)
    private long userIdx;

    @Id
    @Column(name = "auth_code", updatable = false, nullable = false)
    private AuthEnum authCode;

    @OneToOne
    @JoinColumn(name = "user_idx", insertable = false, updatable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "auth_code", insertable = false, updatable = false)
    private Authority authority;

    @Builder
    public Role(String createdIp, String lastUpdatedIp, long userIdx, AuthEnum authCode) {
        super(createdIp, lastUpdatedIp);
        this.userIdx = userIdx;
        this.authCode = authCode;
    }
}
