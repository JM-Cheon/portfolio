package com.jm.portfolio.global.common.base.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseRequest {
    private String createdIp;
    private String lastUpdatedIp;
}
