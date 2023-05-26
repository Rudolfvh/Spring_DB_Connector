package org.example.spring.database.repository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.spring.entity.Company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CompanyRepository {

    private final Connection connection;
    private final String FIND_BY_ID;

    @SneakyThrows
    public Optional<Company> findById(Long id) {
        try (connection;
             var prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            Company company = null;
            prepareStatement.setLong(1, id);
            var resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                company = createCompany(resultSet);
            }
            return Optional.ofNullable(company);
        }
    }

    @SneakyThrows
    private Company createCompany(ResultSet resultSet) {
        return Company.builder()
                .id(resultSet.getLong("company_id"))
                .name(resultSet.getString("company_name"))
                .usersId(createUsersId(resultSet))
                .build();
    }

    @SneakyThrows
    private List<Long> createUsersId(ResultSet resultSet) {
        return Arrays.stream(resultSet.getString("users_id").split("\\s"))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

}
