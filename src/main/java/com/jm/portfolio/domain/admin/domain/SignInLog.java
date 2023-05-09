package com.jm.portfolio.domain.admin.domain;

import com.jm.portfolio.domain.model.Email;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sign_in_log")
@EqualsAndHashCode(of = {"idx"}, callSuper = false)
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name = "sign_in_ip", updatable = false)
    private String signInIp;

    @CreatedDate
    @Column(name = "sign_in_at", updatable = false)
    private LocalDateTime signInAt;

    @Builder
    public SignInLog(Email email, String signInIp) {
        this.email = email;
        this.signInIp = signInIp;
    }
}
