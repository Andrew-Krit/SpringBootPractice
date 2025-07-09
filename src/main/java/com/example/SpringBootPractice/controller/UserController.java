package com.example.SpringBootPractice.controller;

import com.example.SpringBootPractice.dto.UserDto;
import com.example.SpringBootPractice.service.DefaultUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

/**
 * Контроллер для управления пользователями.
 * Обрабатывает HTTP-запросы к REST API по пути "/api/users".
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final DefaultUserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Integer id, @RequestBody UserDto dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
