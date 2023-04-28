package com.jm.portfolio.global.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 1. SpringWeb
 * 2. SpringSecurity
 * 3. JDBC
 * 4. JPA
 * 5. QueryDSL **
 * 6. JWT **
 * 7. Log4j2
 * 8. SpringDoc
 * 9. Lombok
 * 10. Postgresql
 */
// TODO: QueryDSL, Jwt 적용하기
@SpringBootApplication
@ComponentScan(basePackages = {"com.jm.portfolio"})
public class PortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioApplication.class, args);
    }

}
