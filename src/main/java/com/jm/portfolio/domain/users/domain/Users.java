package com.jm.portfolio.domain.users.domain;

import com.jm.portfolio.global.common.base.domain.BaseDomain;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = {"idx"}, callSuper = false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long idx;

    @Column(updatable = false, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private LocalDateTime withdrawAt;

    private String withdrawIp;

    @Column(columnDefinition = "varchar(1) CHECK (is_withdraw IN ('Y', 'N'))")
    private String isWithdraw;

    @Column(columnDefinition = "varchar(1) CHECK (is_disabled IN ('Y', 'N'))")
    private String isDisabled;

    @Column(columnDefinition = "varchar(1) CHECK (is_expired IN ('Y', 'N'))")
    private String isExpired;

    @PrePersist
    public void prePersist() {
        this.isWithdraw = this.isWithdraw == null ? "N" : this.isWithdraw;
        this.isDisabled = this.isDisabled == null ? "N" : this.isDisabled;
        this.isExpired = this.isExpired == null ? "N" : this.isExpired;
    }

    @Builder
    public Users (LocalDateTime createdAt, LocalDateTime lastUpdatedAt, String createdIp, String lastUpdatedIp, String email, String password, String nickname, LocalDateTime withdrawAt, String withdrawIp, String isWithdraw, String isDisabled, String isExpired, BaseDomain baseDomain) {
        super(createdAt, lastUpdatedAt, createdIp, lastUpdatedIp);
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.withdrawAt = withdrawAt;
        this.withdrawIp = withdrawIp;
        this.isWithdraw = isWithdraw;
        this.isDisabled = isDisabled;
        this.isExpired = isExpired;
    }
}
