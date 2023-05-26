package org.example.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.spring.dto.UserReadDto;
import org.example.spring.entity.User;

@RequiredArgsConstructor
public class UserMapper {

    private final CompanyMapper companyMapper;

    public UserReadDto toDto(User user) {
        return new UserReadDto(user.getId(),
                user.getUsername(),
                user.getEmail(),
                companyMapper.toDto(user.getCompany()));
    }

}