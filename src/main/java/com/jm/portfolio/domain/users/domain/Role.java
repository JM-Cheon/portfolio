package com.jm.portfolio.domain.users.domain;

import com.jm.portfolio.global.common.base.domain.BaseDomain;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role")
@IdClass(RolePK.class)
@EqualsAndHashCode(of = {"userIdx", "authCode"}, callSuper = false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role extends BaseDomain {

    @Id
    @Column(name = "user_idx", updatable = false, nullable = false)
    private long userIdx;

    @Id
    @Column(name = "auth_code", updatable = false, nullable = false)
    private String authCode;

    @ManyToOne
    @JoinColumn(name = "auth_code", insertable = false, updatable = false)
    private Authority authority;
}
