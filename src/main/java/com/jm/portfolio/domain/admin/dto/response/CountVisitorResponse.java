package com.jm.portfolio.domain.admin.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "회원관련 통계 Response")
@Getter
@ToString
@NoArgsConstructor
public class CountVisitorResponse {

    @Schema(description = "날짜")
    private LocalDate visitDate;
    @Schema(description = "일일 방문자 수")
    private Long totalVisit;

    public CountVisitorResponse (LocalDate visitDate, Long totalVisit) {
        this.visitDate = visitDate;
        this.totalVisit = totalVisit;
    }
}
