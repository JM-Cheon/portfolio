package com.jm.portfolio.global.init_data;

import com.jm.portfolio.domain.authority.dao.AuthorityRepository;
import com.jm.portfolio.domain.authority.domain.Authority;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile({"local", "dev", "prod", "create"})
public class AuthorityInitData {

    @Bean
    CommandLineRunner authorityInit(AuthorityRepository authorityRepository) {
        return args -> {
            /* Authority 기본 데이터 */
            Authority guest = Authority.builder()
                    .authorityCode("GUEST")
                    .authorityName("임시 사용자")
                    .authorityDesc("회원가입을 하지 않고 게임을 할 수 있는 사용자")
                    .createdIp("init")
                    .lastUpdatedIp("init")
                    .build();

            Authority user = Authority.builder()
                    .authorityCode("USER")
                    .authorityName("사용자")
                    .authorityDesc("일반 사용자")
                    .createdIp("init")
                    .lastUpdatedIp("init")
                    .build();

            Authority interimAdmin = Authority.builder()
                    .authorityCode("INTERIM_ADMIN")
                    .authorityName("임시 관리자")
                    .authorityDesc("관리자 신청 후 인증을 기다리는 관리자")
                    .createdIp("init")
                    .lastUpdatedIp("init")
                    .build();

            Authority admin = Authority.builder()
                    .authorityCode("ADMIN")
                    .authorityName("관리자")
                    .authorityDesc("일반 관리자")
                    .createdIp("init")
                    .lastUpdatedIp("init")
                    .build();

            authorityRepository.saveAll(Arrays.asList(guest, user, interimAdmin, admin));
        };
    }
}
