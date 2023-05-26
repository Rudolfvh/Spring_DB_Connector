package org.example.spring.config;

import org.example.spring.database.ConnectionManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application-prod.properties")
@ComponentScan(basePackages = "org.example.spring.*")
@Import({TestConfig.class,ProdConfig.class})
public class ApplicationConfig {

    @Bean("findUserById")
    public String findUser() {
        return """
                SELECT u.id, u.username, u.email, c.id AS company_id, c.name AS company_name
                FROM users u
                JOIN company c ON u.company_id = c.id WHERE u.id = ?;
                """;
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Connection connect(ConnectionManager connectionManager) {
        var connection = connectionManager.open();
        createTables(connection);
        return connection;
    }

    private void createTables(Connection connection) {
        try (var companyStatement = connection.prepareStatement("""
                CREATE TABLE IF NOT EXISTS company
                (
                    id   serial PRIMARY KEY,
                    name varchar(256) NOT NULL UNIQUE
                );
                """);
             var userStatement = connection.prepareStatement("""
                     CREATE TABLE IF NOT EXISTS users
                       (
                           id         serial PRIMARY KEY,
                           username   varchar(256) NOT NULL,
                           email      varchar(256) NOT NULL UNIQUE,
                           company_id int REFERENCES company (id)
                       );
                     """);
        ) {
            companyStatement.executeUpdate();
            userStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
