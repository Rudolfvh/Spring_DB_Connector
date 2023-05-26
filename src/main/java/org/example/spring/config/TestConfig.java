package org.example.spring.config;

import org.springframework.context.annotation.*;

@Profile("test")
@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan(basePackages = "org.example.spring.*")
public class TestConfig {

    @Bean("findCompanyById")
    public String sqlBean() {
        return """
                SELECT c.id AS company_id, name AS company_name,
                        ARRAY[(SELECT id
                            FROM users u
                            WHERE u.company_id = c.id)]
                    AS users_id
                FROM company c
                WHERE c.id = ?;
                """;
    }



}
