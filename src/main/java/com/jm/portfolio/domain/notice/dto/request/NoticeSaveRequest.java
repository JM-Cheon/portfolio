package com.jm.portfolio.domain.notice.dto.request;

import com.jm.portfolio.domain.notice.domain.Notice;
import com.jm.portfolio.global.common.base.dto.request.BaseRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class NoticeSaveRequest extends BaseRequest {

    private String title;
    private String content;
    private String img;


    public Notice toEntity() {
        return Notice.builder()
                .title(title)
                .content(content)
                .img(img)
                .createdIp(getCreatedIp())
                .lastUpdatedIp(getLastUpdatedIp())
                .build();
    }
}
