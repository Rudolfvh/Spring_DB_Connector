package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.repository.UserRepository;
import org.example.spring.dto.UserReadDto;
import org.example.spring.mapper.UserMapper;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

}