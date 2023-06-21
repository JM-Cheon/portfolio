package com.jm.portfolio.domain.users.domain;

import com.jm.portfolio.domain.model.Birth;
import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.model.Name;
import com.jm.portfolio.global.common.base.domain.BaseDomain;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = {"user_idx"}, callSuper = false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx", updatable = false)
    private Long userIdx;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", nullable = false, unique = true, updatable = false, length = 50))
    private Email email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", nullable = false, length = 50))
    private Name name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "birth", nullable = false, length = 50))
    private Birth birth;

    @Column(name = "withdraw_at")
    private LocalDateTime withdrawAt;

    @Column(name = "withdraw_ip")
    private String withdrawIp;

    @Column(name = "is_withdraw", columnDefinition = "boolean not null default false")
    private boolean isWithdraw;

    @Column(name = "is_disabled", columnDefinition = "boolean not null default false")
    private boolean isDisabled;

    @Column(name = "is_expired", columnDefinition = "boolean not null default false")
    private boolean isExpired;

    @Column(name = "sign_up_with")
    private String signUpWith;

    @Column(name = "snsId")
    private String snsId;

    @OneToMany
    @JoinColumn(name = "user_idx")
    private List<UserRole> userRole;

    @Builder
    public Users (Email email, String password, String nickname, Name name, Birth birth, LocalDateTime withdrawAt, String withdrawIp, boolean isWithdraw, boolean isDisabled, boolean isExpired, String signUpWith, String snsId, List<UserRole> userRole) {
        super();
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.birth = birth;
        this.withdrawAt = withdrawAt;
        this.withdrawIp = withdrawIp;
        this.isWithdraw = isWithdraw;
        this.isDisabled = isDisabled;
        this.isExpired = isExpired;
        this.signUpWith = signUpWith;
        this.snsId = snsId;
        this.userRole = userRole;
    }

    public void hashPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
