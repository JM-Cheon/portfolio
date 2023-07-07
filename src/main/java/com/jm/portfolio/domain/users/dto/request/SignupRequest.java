package com.jm.portfolio.domain.users.dto.request;

import com.jm.portfolio.domain.model.Birth;
import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.model.Name;
import com.jm.portfolio.domain.users.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SignupRequest {
    @Valid
    private String email;
    @Valid
    private String password;
    @Valid
    private String nickname;
    @Valid
    private String name;
    @Valid
    private Birth birth;

    public Users toEntity() {
        return Users.builder()
                .email(Email.of(email))
                .password(password)
                .nickname(nickname)
                .name(Name.of(name))
                .birth(birth)
                .build();
    }
}
