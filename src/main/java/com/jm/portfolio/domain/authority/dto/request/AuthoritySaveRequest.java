package com.jm.portfolio.domain.authority.dto.request;

import com.jm.portfolio.domain.authority.domain.Authority;
import com.jm.portfolio.domain.model.AuthorityEnum;
import com.jm.portfolio.global.common.base.dto.request.BaseRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class AuthoritySaveRequest extends BaseRequest {
    private String authorityCode;
    private String authorityName;
    private String authorityDesc;

    public Authority toEntity() {
        return Authority.builder()
                .authorityCode(authorityCode)
                .authorityName(authorityName)
                .authorityDesc(authorityDesc)
                .createdIp(getCreatedIp())
                .lastUpdatedIp(getLastUpdatedIp())
                .build();
    }
}
