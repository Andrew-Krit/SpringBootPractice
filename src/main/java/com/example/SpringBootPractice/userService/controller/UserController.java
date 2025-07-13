package com.example.SpringBootPractice.userService.controller;

import com.example.SpringBootPractice.userService.dto.UserDto;
import com.example.SpringBootPractice.userService.DefaultUserService;
import com.example.SpringBootPractice.userService.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Контроллер для управления пользователями.
 * Обрабатывает HTTP-запросы к REST API по пути "/api/users".
 */
@Tag(name = "User API", description = "Операции с пользователями")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final DefaultUserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Получить всех пользователей")
    @ApiResponse(responseCode = "200", description = "Список пользователей")
    @GetMapping
    public CollectionModel<UserDto> getAllUsers() {
        List<UserDto> dtos = userService.getAllUsers().stream()
                .map(userMapper::toModel)
                .toList();

        return CollectionModel.of(dtos,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
    }

    @Operation(summary = "Получить пользователя по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return userMapper.toModel(userService.getUserById(id));
    }

    @Operation(summary = "Создать нового пользователя")
    @ApiResponse(responseCode = "201", description = "Пользователь создан")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        UserDto created = userService.createUser(dto);
        return new ResponseEntity<>(userMapper.toModel(created), HttpStatus.CREATED);
    }

    @Operation(summary = "Обновить пользователя")
    @ApiResponse(responseCode = "200", description = "Пользователь обновлён")
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Integer id, @RequestBody UserDto dto) {
        UserDto updated = userService.updateUser(id, dto);

        return userMapper.toModel(updated);
    }

    @Operation(summary = "Удалить пользователя")
    @ApiResponse(responseCode = "204", description = "Пользователь удалён")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}
