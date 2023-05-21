package com.jm.portfolio.domain.users.dto.request;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SigninRequest {
    @Valid
    private Email email;
    @Valid
    private String password;
    @Valid
    private String signInIp;

    public Users toEntity() {
        return Users.builder()
                .email(email)
                .password(password)
                .build();
    }
}
