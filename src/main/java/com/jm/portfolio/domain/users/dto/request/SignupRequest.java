package com.jm.portfolio.domain.users.dto.request;

import com.jm.portfolio.domain.model.Birth;
import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.model.Name;
import com.jm.portfolio.global.common.base.dto.request.BaseRequest;
import com.jm.portfolio.domain.users.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SignupRequest extends BaseRequest {
    @Valid
    private Email email;
    @Valid
    private String password;
    @Valid
    private String nickname;
    @Valid
    private Name name;
    @Valid
    private Birth birth;

    public Users toEntity() {
        return Users.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .name(name)
                .birth(birth)
                .createdIp(getCreatedIp())
                .lastUpdatedIp(getLastUpdatedIp())
                .build();
    }
}
