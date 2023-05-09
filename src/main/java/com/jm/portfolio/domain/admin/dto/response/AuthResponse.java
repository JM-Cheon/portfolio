package com.jm.portfolio.domain.admin.dto.response;

import com.jm.portfolio.domain.admin.domain.Authority;
import com.jm.portfolio.global.common.base.dto.response.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class AuthResponse extends BaseResponse {
    private String authCode;
    private String authName;
    private String authDesc;

    public AuthResponse(final Authority authority) {
        super(authority.getCreatedAt(), authority.getLastUpdatedAt(), authority.getCreatedIp(), authority.getLastUpdatedIp());
        this.authCode = authority.getAuthCode();
        this.authName = authority.getAuthName();
        this.authDesc = authority.getAuthDesc();
    }
}
