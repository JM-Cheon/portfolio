package com.jm.portfolio.global.jwt.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class TokenResponse {

    private String grantType;
    private String accessToken;
    private long accessTokenExpiresIn;
    private String refreshToken;
    private long refreshTokenExpiresIn;

    public TokenResponse(final String grantType, final String accessToken, final long accessTokenExpiresIn) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

    public TokenResponse(final String grantType, final String accessToken, final long accessTokenExpiresIn, final String refreshToken, final long refreshTokenExpiresIn) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }
}
