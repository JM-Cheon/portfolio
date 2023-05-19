package com.jm.portfolio.domain.users.domain;

import com.jm.portfolio.domain.model.Birth;
import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.model.Name;
import com.jm.portfolio.domain.authority.domain.UserRole;
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

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "first", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "middle", column = @Column(name = "middle_name")),
            @AttributeOverride(name = "last", column = @Column(name = "last_name", nullable = false))
    })
    private Name name;;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "year", column = @Column(name = "year", nullable = false)),
            @AttributeOverride(name = "month", column = @Column(name = "month")),
            @AttributeOverride(name = "day", column = @Column(name = "day", nullable = false))
    })
    private Birth birth;;

    @Column(name = "withdraw_at")
    private LocalDateTime withdrawAt;

    @Column(name = "withdraw_ip")
    private String withdrawIp;

    @Column(name = "is_withdraw", columnDefinition = "varchar(1) CHECK (is_withdraw IN ('Y', 'N'))")
    private String isWithdraw;

    @Column(name = "is_disabled", columnDefinition = "varchar(1) CHECK (is_disabled IN ('Y', 'N'))")
    private String isDisabled;

    @Column(name = "is_expired", columnDefinition = "varchar(1) CHECK (is_expired IN ('Y', 'N'))")
    private String isExpired;

    @OneToMany
    @JoinColumn(name = "user_idx")
    private List<UserRole> userRole;

    @PrePersist
    public void prePersist() {
        this.isWithdraw = this.isWithdraw == null ? "N" : this.isWithdraw;
        this.isDisabled = this.isDisabled == null ? "N" : this.isDisabled;
        this.isExpired = this.isExpired == null ? "N" : this.isExpired;
    }

    @Builder
    public Users (String createdIp, String lastUpdatedIp, Email email, String password, String nickname, Name name, Birth birth, LocalDateTime withdrawAt, String withdrawIp, String isWithdraw, String isDisabled, String isExpired, List<UserRole> userRole) {
        super(createdIp, lastUpdatedIp);
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
        this.userRole = userRole;
    }

    public Users hashPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
        return this;
    }
}
