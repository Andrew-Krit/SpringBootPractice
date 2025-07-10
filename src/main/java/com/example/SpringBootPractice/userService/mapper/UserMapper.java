package com.example.SpringBootPractice.userService.mapper;

import com.example.SpringBootPractice.userService.dto.UserDto;
import com.example.SpringBootPractice.userService.entity.User;
import org.springframework.stereotype.Component;

/**
 * Класс для преобразования между сущностью User и UserDto.
 */
@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAge());
    }

    public User toEntity(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getAge());
    }
}
