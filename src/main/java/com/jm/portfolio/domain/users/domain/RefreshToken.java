package com.jm.portfolio.domain.users.domain;

import com.jm.portfolio.global.util.IpUtil;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.PrePersist;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 14)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    private String email;
    private String refreshToken;
    private Long expiresIn;
    private String createdIp;

    @Builder
    public RefreshToken(final String email, final String refreshToken, final Long expiresIn) {
        this.email = email;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        this.createdIp = IpUtil.getClientIp(request);
    }
}
