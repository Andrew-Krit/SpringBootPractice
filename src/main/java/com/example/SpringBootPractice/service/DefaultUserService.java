package com.example.SpringBootPractice.service;

import com.example.SpringBootPractice.entity.User;
import com.example.SpringBootPractice.dto.UserDto;
import com.example.SpringBootPractice.mapper.UserMapper;
import com.example.SpringBootPractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервисный слой, использующий userRepository.
 */
@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDto createUser(UserDto dto) {
        User saved = userRepository.save(userMapper.toEntity(dto));
        return userMapper.toDto(saved);
    }

    public UserDto updateUser(Integer id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        return userMapper.toDto(userRepository.save(user));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
