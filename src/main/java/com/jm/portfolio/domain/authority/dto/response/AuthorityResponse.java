package com.jm.portfolio.domain.authority.dto.response;

import com.jm.portfolio.domain.authority.domain.Authority;
import com.jm.portfolio.domain.model.AuthorityEnum;
import com.jm.portfolio.global.common.base.dto.response.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class AuthorityResponse extends BaseResponse {
    private String autholityCode;
    private String autholityName;
    private String autholityDesc;

    public AuthorityResponse(final Authority authority) {
        super(authority.getCreatedAt(), authority.getLastUpdatedAt(), authority.getCreatedIp(), authority.getLastUpdatedIp());
        this.autholityCode = authority.getAuthorityCode();
        this.autholityName = authority.getAuthorityName();
        this.autholityDesc = authority.getAuthorityDesc();
    }
}
