package com.jm.portfolio.global.common.dto.response;

import lombok.*;
import org.yaml.snakeyaml.constructor.BaseConstructor;

import java.util.Date;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseResponse {
    private Date createdAt;
    private Date lastUpdatedAt;
    private String createdIp;
    private String lastUpdatedIp;

    public BaseResponse (Date createdAt, Date lastUpdatedAt, String createdIp, String lastUpdatedIp) {
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.createdIp = createdIp;
        this.lastUpdatedIp = lastUpdatedIp;
    }
}
