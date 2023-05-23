package com.jm.portfolio.domain.admin.dto.response;

import com.jm.portfolio.domain.admin.domain.SignInLog;
import com.jm.portfolio.domain.model.Email;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "로그인 이력 Response")
@Getter
@ToString
@NoArgsConstructor
public class SignInLogResponse {

    @Schema(description = "인덱스")
    private Long idx;
    @Schema(description = "아메일")
    private Email email;
    @Schema(description = "로그인 위치")
    private String signInIp;
    @Schema(description = "로그인 시간")
    private LocalDateTime signInAt;

    public SignInLogResponse(final SignInLog signInLog) {
        this.idx = signInLog.getIdx();
        this.email = signInLog.getEmail();
        this.signInIp = signInLog.getSignInIp();
        this.signInAt = signInLog.getSignInAt();
    }
}
