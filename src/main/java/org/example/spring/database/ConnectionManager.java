package org.example.spring.database;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

@RequiredArgsConstructor
public class ConnectionManager {

    private final String url;
    private final String username;
    private final String password;

    @SneakyThrows
    public Connection open() {
        return DriverManager.getConnection(url, username, password);
    }

}
