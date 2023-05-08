package com.jm.portfolio.global.common.paging;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class SearchCondition implements Serializable {
    private int pageNo;				//요청한 페이지 번호
    private int amount = 10; 			//게시물 수
    private String sortBy;          //정렬할 컬럼
    private String orderBy;         //정렬 순서 (오름차, 내림차)
    private String searchBy;
    private String searchValue;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public SearchCondition(int pageNo, String sortBy, String orderBy, String searchBy, String searchValue, LocalDateTime startDate, LocalDateTime endDate) {
        this.pageNo = pageNo;
        this.sortBy = sortBy;
        this.orderBy = orderBy;
        this.searchBy = searchBy;
        this.searchValue = searchValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
