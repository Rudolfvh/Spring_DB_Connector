package org.example.spring.mapper;

import org.example.spring.dto.CompanyReadDto;
import org.example.spring.entity.Company;

public class CompanyMapper {

    public CompanyReadDto toDto(Company company) {
        return new CompanyReadDto(company.getId(), company.getName());
    }

}