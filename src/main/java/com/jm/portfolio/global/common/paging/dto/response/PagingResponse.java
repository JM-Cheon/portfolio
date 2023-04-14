package com.jm.portfolio.global.common.paging.dto.response;

import com.jm.portfolio.global.common.paging.dto.PagingDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingResponse {
    private Object data;
    private PagingDTO pageInfo;
}
