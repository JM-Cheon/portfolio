package com.jm.portfolio.global.common.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingResponse {
    private Object data;
    private PagingDTO pageInfo;
}
