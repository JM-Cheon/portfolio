package com.jm.portfolio.global.init_data;

import com.jm.portfolio.domain.users.dao.UserRoleRepository;
import com.jm.portfolio.domain.users.domain.UserRole;
import com.jm.portfolio.domain.model.AuthorityEnum;
import com.jm.portfolio.domain.model.Birth;
import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.model.Name;
import com.jm.portfolio.domain.users.dao.UserRepository;
import com.jm.portfolio.domain.users.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@Profile({"local", "dev"})
@RequiredArgsConstructor
public class TestInitData {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner testInit(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        return args -> {
            /* 계정 생성 */
            // ADMIN
            Users admin = Users.builder()
                    .email(Email.of("admin@gmail.com"))
                    .password("admin")
                    .nickname("admin")
                    .name(Name.of("리자", null, "관"))
                    .birth(Birth.of("1997", "05", "01"))
                    .build();
            admin.hashPassword(passwordEncoder);

            // USER
            Users user01 = Users.builder()
                    .email(Email.of("user01@gmail.com"))
                    .password("user01")
                    .nickname("user01")
                    .name(Name.of("길동", null, "홍"))
                    .birth(Birth.of("1988", "10", "10"))
                    .build();
            user01.hashPassword(passwordEncoder);

            Users user02 = Users.builder()
                    .email(Email.of("user02@gmail.com"))
                    .password("user02")
                    .nickname("user02")
                    .name(Name.of("몽룡", "존", "이"))
                    .birth(Birth.of("1987", "12", "10"))
                    .build();
            user02.hashPassword(passwordEncoder);

            userRepository.saveAll(Arrays.asList(admin, user01, user02));

            // ADMIN
            UserRole adminRole = UserRole.builder()
                    .userIdx(1L)
                    .authorityCode(AuthorityEnum.ADMIN.getAuth())
                    .build();

            // USER
            UserRole user01Role = UserRole.builder()
                    .userIdx(2L)
                    .authorityCode(AuthorityEnum.USER.getAuth())
                    .build();

            UserRole user02Role = UserRole.builder()
                    .userIdx(3L)
                    .authorityCode(AuthorityEnum.USER.getAuth())
                    .build();

            userRoleRepository.saveAll(Arrays.asList(adminRole, user01Role, user02Role));
        };
    }
}
