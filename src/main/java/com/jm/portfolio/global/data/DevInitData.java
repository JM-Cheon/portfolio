package com.jm.portfolio.global.data;

import com.jm.portfolio.domain.authority.dao.AuthorityRepository;
import com.jm.portfolio.domain.authority.domain.Authority;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"local", "dev"})
public class DevInitData {

    @Bean
    CommandLineRunner init(AuthorityRepository authorityRepository) {
        return args -> {
            Authority guest = Authority.builder()

                    .build();
        };
    }
}
