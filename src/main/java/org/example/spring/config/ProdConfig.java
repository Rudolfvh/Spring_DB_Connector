package org.example.spring.config;

import org.springframework.context.annotation.*;

@Profile("prod")
@Configuration
@PropertySource("classpath:application-prod.properties")
@ComponentScan(basePackages = "org.example.spring.*")
public class ProdConfig {

    @Bean("findCompanyById")
    public String findCompany   () {
        return """
                SELECT c.id AS company_id, name AS company_name,
                    array_to_string(
                        ARRAY(SELECT id
                            FROM users u
                            WHERE u.company_id = c.id), ' ')
                    AS users_id
                FROM company c
                WHERE c.id = ?;
                """;
    }

}
