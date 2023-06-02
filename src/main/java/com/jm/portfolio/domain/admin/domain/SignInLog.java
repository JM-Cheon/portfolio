package com.jm.portfolio.domain.admin.domain;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.global.util.IpUtil;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "sign_in_log")
@EqualsAndHashCode(of = {"idx"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", updatable = false)
    private Long idx;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", nullable = false, updatable = false, length = 50))
    private Email email;

    @Column(name = "is_success", updatable = false)
    private boolean isSuccess;

    @Column(name = "sign_in_ip", updatable = false)
    private String signInIp;

    @CreatedDate
    @Column(name = "sign_in_at", updatable = false)
    private LocalDateTime signInAt;

    @PrePersist
    public void ipSet() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        this.signInIp = IpUtil.getClientIp(request);
    }

    @Builder
    public SignInLog(Email email, boolean isSuccess) {
        this.email = email;
        this.isSuccess = isSuccess;
    }
}
