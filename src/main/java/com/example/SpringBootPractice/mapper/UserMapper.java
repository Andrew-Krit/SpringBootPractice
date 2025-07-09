package com.example.SpringBootPractice.mapper;

import com.example.SpringBootPractice.dto.UserDto;
import com.example.SpringBootPractice.entity.User;

/**
 * Класс для преобразования между сущностью User и UserDto.
 */
public class UserMapper {
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAge());
    }

    public User toEntity(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getAge());
    }
}
