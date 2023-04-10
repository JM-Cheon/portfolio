package com.jm.portfolio.domain.common.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseResponse {
    private Date createdAt;
    private Date lastUpdatedAt;
    private String createdIp;
    private String lastUpdatedIp;
}
