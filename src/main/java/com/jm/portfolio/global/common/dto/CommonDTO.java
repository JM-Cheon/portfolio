package com.jm.portfolio.global.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@Schema(description = "기본 정보 DTO")
public class CommonDTO {

    @Schema(description = "생성 일자")
    private Date createdAt;
    @Schema(description = "마지막 수정 일자")
    private Date lastUpdatedAt;
    @Schema(description = "생성 IP")
    private String createdIp;
    @Schema(description = "마지막 수정 IP")
    private String lastUpdatedIp;

//    @Builder
//    public BaseDTO(Date createdAt, Date lastUpdatedAt, String createdIp, String lastUpdatedIp) {
//        this.createdAt = createdAt;
//        this.lastUpdatedAt = lastUpdatedAt;
//        this.createdIp = createdIp;
//        this.lastUpdatedIp = lastUpdatedIp;
//    }
}
