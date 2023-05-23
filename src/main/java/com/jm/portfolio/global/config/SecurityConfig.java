package com.jm.portfolio.global.config;

import com.jm.portfolio.domain.model.AuthorityEnum;
import com.jm.portfolio.global.jwt.JwtAccessDeniedHandler;
import com.jm.portfolio.global.jwt.JwtAuthenticationEntryPoint;
import com.jm.portfolio.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers(
                "/api-docs/**",
                "/swagger-ui/**"
        );
    }

    /* 3. HTTP요청에 대한 권한별 설정(세션 인증 -> 토큰 인증으로 인해 바뀐 부분 존재) */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .exceptionHandling()
                /* 기본 시큐리티 설정에서 JWT 토큰과 관련된 유효성과 권한 체크용 설정 */
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)	// 유효한 자격 증명 없을 시(401)
                .accessDeniedHandler(jwtAccessDeniedHandler)			// 필요한 권한 없이 접근 시(403)
                .and()
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // cors
                .antMatchers("/api/v1/auth/**").permitAll()
		    	.antMatchers("/api/v1/user/**").hasAnyRole(
                        AuthorityEnum.GUEST.getAuth(),
                        AuthorityEnum.USER.getAuth(),
                        AuthorityEnum.INTERIM_ADMIN.getAuth(),
                        AuthorityEnum.ADMIN.getAuth()
                )
		    	.antMatchers("/api/v1/admin/**").hasRole(AuthorityEnum.ADMIN.getAuth())
//		    	.anyRequest().permitAll();	// security 설정 완료 후 삭제
                .and()
                /* 세션 인증 방식을 쓰지 않겠다는 설정 */
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                /* jwt 토큰 방식을 쓰겠다는 설정 */
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }

    /* 4. CORS 설정용 Bean(허용 할 origin과 httpMethod 종류와 header 값) */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList(
                "Access-Control-Allow-Origin", "Content-type",
                "Access-Control-Allow-Headers", "Authorization",
                "X-Requested-With"
        ));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
