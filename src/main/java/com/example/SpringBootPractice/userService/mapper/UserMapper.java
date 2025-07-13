package com.example.SpringBootPractice.userService.mapper;

import com.example.SpringBootPractice.userService.controller.UserController;
import com.example.SpringBootPractice.userService.dto.UserDto;
import com.example.SpringBootPractice.userService.entity.User;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Класс для преобразования между сущностью User и UserDto.
 */
@Component
public class UserMapper implements RepresentationModelAssembler<UserDto, UserDto> {
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAge());
    }

    public User toEntity(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getAge());
    }

    @Override
    public UserDto toModel(UserDto dto) {
        dto.add(linkTo(methodOn(UserController.class).getUserById(dto.getId())).withSelfRel());
        dto.add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users"));
        dto.add(linkTo(methodOn(UserController.class).updateUser(dto.getId(), dto)).withRel("update"));
        dto.add(linkTo(methodOn(UserController.class).deleteUser(dto.getId())).withRel("delete"));
        return dto;
    }
}
