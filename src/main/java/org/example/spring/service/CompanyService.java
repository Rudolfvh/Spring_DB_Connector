package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.repository.CompanyRepository;
import org.example.spring.dto.CompanyReadDto;
import org.example.spring.mapper.CompanyMapper;

import java.util.Optional;

@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public Optional<CompanyReadDto> findById(Long id) {
        return companyRepository.findById(id).map(companyMapper::toDto);
    }

}
