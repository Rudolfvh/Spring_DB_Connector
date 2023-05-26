package org.example.spring.database;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class ConnectionManager {

    private final String url;
    private final String username;
    private final String password;

    public ConnectionManager(@Value("${db.url}") String url,
                             @Value("${db.username}") String username,
                             @Value("${db.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @SneakyThrows
    public Connection open() {
        return DriverManager.getConnection(url, username, password);
    }

}
