package com.jm.portfolio.domain.users.dto.request;

import com.jm.portfolio.domain.common.dto.request.BaseRequest;
import com.jm.portfolio.domain.users.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserSignupRequest extends BaseRequest {
    @Valid
    private String email;
    @Valid
    private String password;
    @Valid
    private String nickname;

    public Users toEntity() {
        return Users.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .createdIp(getCreatedIp())
                .lastUpdatedIp(getLastUpdatedIp())
                .build();
    }
}
