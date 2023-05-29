package com.jm.portfolio.domain.users.dto.request;

import com.jm.portfolio.domain.model.Birth;
import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.model.Name;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "회원 정보 DTO")
@Getter
@ToString
@NoArgsConstructor
public class UserModificationRequest {

    @Schema(description = "이메일")
    private Email email;
    @Schema(description = "닉네임")
    private String nickname;
    @Schema(description = "이름")
    private Name name;
    @Schema(description = "생년월일")
    private Birth birth;
    @Schema(description = "계정 활성화 여부")
    private boolean isDisabled;
    @Schema(description = "계정 만료 여부")
    private boolean isExpired;
}
