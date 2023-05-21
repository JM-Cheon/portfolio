package com.jm.portfolio.global.jwt.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class TokenResponse {

    private String grantType;
    private String nickname;
    private String accessToken;
    private long accessTokenExpiresIn;

    public TokenResponse(final String grantType, final String nickname, final String accessToken, final long accessTokenExpiresIn) {
        this.grantType = grantType;
        this.nickname = nickname;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }
}
