package com.jm.portfolio.global.common.base;

import lombok.*;
import org.yaml.snakeyaml.constructor.BaseConstructor;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseResponse {
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private String createdIp;
    private String lastUpdatedIp;

    public BaseResponse (LocalDateTime createdAt, LocalDateTime lastUpdatedAt, String createdIp, String lastUpdatedIp) {
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.createdIp = createdIp;
        this.lastUpdatedIp = lastUpdatedIp;
    }
}
