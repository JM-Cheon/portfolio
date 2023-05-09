package com.jm.portfolio.domain.admin.dto.response;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class AuthListResponse {
    private final int count;
    private final List<AuthResponse> authResponse;

    public AuthListResponse (int count, List<AuthResponse> authResponse) {
        this.count = count;
        this.authResponse = authResponse;
    }
}
