//package com.jm.portfolio.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final ObjectMapper objectMapper;
//
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring().mvcMatchers(
////                "/v3/api-docs/**",
////                "/swagger-ui/**",
////                "/api/v1/login" // 임시
//        );
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.antMatcher("/**")
//                .httpBasic().disable()
//                .formLogin().disable()
//                .cors().disable()
//                .csrf().disable()
//                .authorizeRequests()
//                .anyRequest().permitAll()
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(((request, response, accessDeniedException) -> {
//                    response.setStatus(HttpStatus.FORBIDDEN.value());
//                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//                    objectMapper.writeValue(
//                            response.getOutputStream(),
//                            ExceptionResponse.of(ExceptionCode.FAIL_AUTHORIZATION)
//                    );
//                })).and().build();
//    }
//}
