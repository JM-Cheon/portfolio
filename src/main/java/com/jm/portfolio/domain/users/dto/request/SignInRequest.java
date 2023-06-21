package com.jm.portfolio.domain.users.dto.request;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SignInRequest {
    @Valid
    private String email;
    @Valid
    private String password;

    public Users toEntity() {
        return Users.builder()
                .email(Email.of(email))
                .password(password)
                .build();
    }
}
