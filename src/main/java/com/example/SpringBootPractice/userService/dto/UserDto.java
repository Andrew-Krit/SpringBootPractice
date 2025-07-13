package com.example.SpringBootPractice.userService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

/**
 * Класс UserDto для разделения бизнес-логики.
 */
@Schema(description = "DTO пользователя")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends RepresentationModel<UserDto> {
    @Schema(description = "Идентификатор пользователя", example = "1")
    private Integer id;

    @Schema(description = "Имя пользователя", example = "Andrew")
    private String name;

    @Schema(description = "Email пользователя", example = "andrew@example.com")
    private String email;

    @Schema(description = "Возраст пользователя", example = "25")
    private Integer age;
}
