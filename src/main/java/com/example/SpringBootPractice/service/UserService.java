package com.example.SpringBootPractice.service;

import com.example.SpringBootPractice.entity.User;
import com.example.SpringBootPractice.dto.UserDto;
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
public class UserService implements IUserService {
    private final UserRepository userRepository;

    private UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAge());
    }

    private User mapToEntity(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getAge());
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Integer id) {
        return userRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDto createUser(UserDto dto) {
        User saved = userRepository.save(mapToEntity(dto));

        return mapToDto(saved);
    }

    public UserDto updateUser(Integer id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        return mapToDto(userRepository.save(user));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
