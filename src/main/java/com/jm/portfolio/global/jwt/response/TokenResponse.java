package com.jm.portfolio.global.jwt.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class TokenResponse {

    private String grantType;
    private String email;
    private String accessToken;
    private long accessTokenExpiresIn;

    public TokenResponse(final String grantType, final String email, final String accessToken, final long accessTokenExpiresIn) {
        this.grantType = grantType;
        this.email = email;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }
}
