package com.jm.portfolio.domain.authority.dto.response;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class AuthorityListResponse {
    private final int count;
    private final List<AuthorityResponse> authorityResponse;

    public AuthorityListResponse(int count, List<AuthorityResponse> authorityResponse) {
        this.count = count;
        this.authorityResponse = authorityResponse;
    }
}
