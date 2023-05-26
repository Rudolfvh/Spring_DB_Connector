package org.example.spring.database.repository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.spring.entity.Company;
import org.example.spring.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class UserRepository {

    private final Connection connection;
    private final String FIND_BY_ID;

    public UserRepository(Connection connection,
                          @Qualifier("findUserById") String findByIdQuery) {
        this.connection = connection;
        FIND_BY_ID = findByIdQuery;
    }

    @SneakyThrows
    public Optional<User> findById(Long id) {
        try (connection;
             var prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            User user = null;
            prepareStatement.setLong(1, id);
            var resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    @SneakyThrows
    private User createUser(ResultSet resultSet) {
        return User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .email(resultSet.getString("email"))
                .company(createCompany(resultSet))
                .build();
    }

    @SneakyThrows
    private Company createCompany(ResultSet resultSet) {
        return Company.builder()
                .id(resultSet.getLong("company_id"))
                .name(resultSet.getString("company_name"))
                .build();
    }

}
