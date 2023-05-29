package com.jm.portfolio.global.jwt;

import com.jm.portfolio.domain.authority.domain.UserRole;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.global.jwt.exception.TokenException;
import com.jm.portfolio.global.jwt.response.TokenResponse;
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
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;

    private final UserDetailsService userDetailsService;

    private final Key key;

    public TokenProvider (@Value("${jwt.secret}") String secretKey, UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
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
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return new TokenResponse(BEARER_TYPE, user.getNickname(), accessToken, accessTokenExpiresIn.getTime());
    }

    public String getUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Token 인증 객체 추출
    public Authentication getAuthentication(String token) {

        Claims claims = parseClaims(token);

        if(claims.get(AUTHORITIES_KEY) == null) {
            throw new TokenException("[ TokenProvider ] 권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        log.info("[TokenProvider] authorities {}", authorities);

        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Token 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.debug("[ TokenProvider ] 잘못된 JWT 서명입니다.");
            throw new TokenException("[ TokenProvider ] 잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.debug("[ TokenProvider ] 만료된 JWT 서명입니다.");
            throw new TokenException("[ TokenProvider ] 만료된 JWT 서명입니다.");
        } catch (UnsupportedJwtException e) {
            log.debug("[ TokenProvider ] 지원하지 않는 JWT 서명입니다.");
            throw new TokenException("[ TokenProvider ] 지원하지 않는 JWT 서명입니다.");
        } catch (IllegalArgumentException e) {
            log.debug("[ TokenProvider ] JWT가 잘못되었습니다.");
            throw new TokenException("[ TokenProvider ] JWT가 잘못되었습니다.");
        }
    }

    // Token Claims 추출
    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            log.debug("[ TokenProvider ] 만료된 JWT 서명입니다.");
            return e.getClaims();
        }
    }
}
