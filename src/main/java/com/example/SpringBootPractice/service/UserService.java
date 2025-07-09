package com.example.SpringBootPractice.service;

import com.example.SpringBootPractice.dto.UserDto;
import java.util.List;

/**
 * Интерфейс сервиса для управления пользователями.
 * Определяет стандартные операции CRUD с использованием UserDto.
 */
public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Integer id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Integer id, UserDto userDto);
    void deleteUser(Integer id);
}
