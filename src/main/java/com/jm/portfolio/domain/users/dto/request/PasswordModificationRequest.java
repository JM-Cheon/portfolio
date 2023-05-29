package com.jm.portfolio.domain.users.dto.request;

import com.jm.portfolio.domain.model.Birth;
import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.model.Name;
import com.jm.portfolio.domain.users.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "회원 정보 DTO")
@Getter
@ToString
@NoArgsConstructor
public class PasswordModificationRequest {

    @Schema(description = "이메일")
    private Email email;
    @Schema(description = "기존 비밀번호")
    private String password;
    @Schema(description = "변경 할 비밀번호")
    private String modifyPassword;

    public Users toEntity() {
        return Users.builder()
                .email(email)
                .password(modifyPassword)
                .build();
    }
}
