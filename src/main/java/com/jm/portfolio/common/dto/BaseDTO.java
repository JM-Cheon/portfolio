package com.jm.portfolio.common.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class BaseDTO {

    private Date createdAt;
    private Date lastUpdatedAt;
    private String createdIp;
    private String lastUpdatedIp;

//    @Builder
//    public BaseDTO(Date createdAt, Date lastUpdatedAt, String createdIp, String lastUpdatedIp) {
//        this.createdAt = createdAt;
//        this.lastUpdatedAt = lastUpdatedAt;
//        this.createdIp = createdIp;
//        this.lastUpdatedIp = lastUpdatedIp;
//    }
}
