package com.jm.portfolio.domain.admin.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Schema(description = "회원관련 통계 Response")
@Getter
@ToString
@NoArgsConstructor
public class StatisticsResponse {

    @Schema(description = "총 회원수")
    private Long totalUser;
    @Schema(description = "일일 방문자 수")
    private Long todayVisit;
    @Schema(description = "이달 총 방문자 수")
    private Long monthVisit;
    @Schema(description = "이번달 방문자 수 날짜별 리스트")
    private List<CountVisitorResponse> monthVisitList;

    public StatisticsResponse (Long totalUser, Long todayVisit, Long monthVisit, List<CountVisitorResponse> monthVisitList) {
        this.totalUser = totalUser;
        this.todayVisit = todayVisit;
        this.monthVisit = monthVisit;
        this.monthVisitList = monthVisitList;
    }
}
