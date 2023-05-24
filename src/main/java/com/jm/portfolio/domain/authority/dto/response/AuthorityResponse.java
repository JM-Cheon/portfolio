package com.jm.portfolio.domain.authority.dto.response;

import com.jm.portfolio.domain.authority.domain.Authority;
import com.jm.portfolio.global.common.base.dto.response.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class AuthorityResponse extends BaseResponse {
    private String authorityCode;
    private String authorityName;
    private String authorityDesc;

    public AuthorityResponse(final Authority authority) {
        super(authority.getCreatedAt(), authority.getLastUpdatedAt(), authority.getCreatedIp(), authority.getLastUpdatedIp());
        this.authorityCode = authority.getAuthorityCode();
        this.authorityName = authority.getAuthorityName();
        this.authorityDesc = authority.getAuthorityDesc();
    }
}
