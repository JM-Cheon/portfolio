package com.jm.portfolio.domain.admin.dto.request;

import com.jm.portfolio.domain.admin.domain.Authority;
import com.jm.portfolio.global.common.base.dto.request.BaseRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class AuthSaveRequest extends BaseRequest {
    private String authCode;
    private String authName;
    private String authDesc;

    public Authority toEntity() {
        return Authority.builder()
                .authCode(authCode)
                .authName(authName)
                .authDesc(authDesc)
                .createdIp(getCreatedIp())
                .lastUpdatedIp(getLastUpdatedIp())
                .build();
    }
}
