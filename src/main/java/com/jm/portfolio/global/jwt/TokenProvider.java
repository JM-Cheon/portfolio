package com.jm.portfolio.global.jwt;

import com.jm.portfolio.domain.users.domain.UserRole;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.global.jwt.exception.AccessExpiredException;
import com.jm.portfolio.global.jwt.exception.RefreshExpiredException;
import com.jm.portfolio.global.jwt.exception.TokenException;
import com.jm.portfolio.global.jwt.dto.response.TokenResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private static final String BEARER_TYPE = "Bearer";
    private static final String AUTHORITIES_KEY = "auth";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 2; // 2시간으로 설정
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14; // 2주로 설정

    private final UserDetailsService userDetailsService;

    private final Key accessKey;
    private final Key refreshKey;

    public TokenProvider (
            @Value("${jwt.secret.access}") String accessSecretKey,
            @Value("${jwt.secret.refresh}") String refreshSecretKey,
            UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
        byte[] accessKeyBytes = Decoders.BASE64.decode(accessSecretKey);
        byte[] refreshKeyBytes = Decoders.BASE64.decode(refreshSecretKey);
        this.accessKey = Keys.hmacShaKeyFor(accessKeyBytes);
        this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
    }

    // Token 생성
    public TokenResponse generatedToken (Users user) {

        List<String> roles = new ArrayList<>();
        for(UserRole userRole : user.getUserRole()) {
            roles.add(userRole.getAuthorityCode());
        }

        Claims claims = Jwts.claims().setSubject(user.getEmail().getValue());
        claims.put(AUTHORITIES_KEY, roles);

        long now = System.currentTimeMillis();
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = accessTokenGenerator(accessTokenExpiresIn, claims);
        String refreshToken = refreshTokenGenerator(refreshTokenExpiresIn, claims);

        return new TokenResponse(BEARER_TYPE, accessToken, accessTokenExpiresIn.getTime(), refreshToken, refreshTokenExpiresIn.getTime());
    }

    public TokenResponse reissueAccessToken(String refreshToken) {

        Claims claims = refreshParseClaims(refreshToken);

        long now = System.currentTimeMillis();

        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

        String accessToken = accessTokenGenerator(accessTokenExpiresIn, claims);

        return new TokenResponse(BEARER_TYPE, accessToken, accessTokenExpiresIn.getTime());
    }
    public String accessTokenGenerator (Date expiresIn, Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiresIn)
                .signWith(accessKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String refreshTokenGenerator (Date expiresIn, Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiresIn)
                .signWith(refreshKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(accessKey).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Token 인증 객체 추출
    public Authentication getAuthentication(String token) {

        Claims claims = accessParseClaims(token);

        if(claims.get(AUTHORITIES_KEY) == null) {
            throw new TokenException("[ TokenProvider ] 권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Token 유효성 검사
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new TokenException("[ TokenProvider ] 잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new AccessExpiredException("[ TokenProvider ] 만료된 JWT 서명입니다.");
        } catch (UnsupportedJwtException e) {
            throw new TokenException("[ TokenProvider ] 지원하지 않는 JWT 서명입니다.");
        } catch (IllegalArgumentException e) {
            throw new TokenException("[ TokenProvider ] JWT가 잘못되었습니다.");
        }
    }

    public void validateRefreshToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(refreshKey).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.debug(e.getMessage());
            throw new TokenException("[ TokenProvider ] 잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new RefreshExpiredException("[ TokenProvider ] 만료된 JWT 서명입니다.");
        } catch (UnsupportedJwtException e) {
            throw new TokenException("[ TokenProvider ] 지원하지 않는 JWT 서명입니다.");
        } catch (IllegalArgumentException e) {
            throw new TokenException("[ TokenProvider ] JWT가 잘못되었습니다.");
        }
    }

    // Token Claims 추출
    private Claims accessParseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private Claims refreshParseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(refreshKey).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
