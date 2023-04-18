package com.jm.portfolio.domain.users.dto.request;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.global.common.base.dto.request.BaseRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SigninRequest extends BaseRequest {
    @Valid
    private Email email;
    @Valid
    private String password;
}
