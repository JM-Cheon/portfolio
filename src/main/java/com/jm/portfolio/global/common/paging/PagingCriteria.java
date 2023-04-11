package com.jm.portfolio.global.common.paging;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class PagingCriteria implements Serializable {
    private int pageNo = 1;				//요청한 페이지 번호
    private int amount = 10;				//게시물 수
}
