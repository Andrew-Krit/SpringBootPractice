package com.example.SpringBootPractice.service;

import com.example.SpringBootPractice.dto.UserDto;
import java.util.List;

public interface IUserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Integer id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Integer id, UserDto userDto);
    void deleteUser(Integer id);
}
