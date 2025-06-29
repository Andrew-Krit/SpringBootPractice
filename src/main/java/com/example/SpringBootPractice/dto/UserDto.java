package com.example.SpringBootPractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс UserDto для разделения бизнес-логики.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private Integer age;
}
