package org.example.spring.dto;

public record UserReadDto(Long id, String username, String email, CompanyReadDto company) {
}
