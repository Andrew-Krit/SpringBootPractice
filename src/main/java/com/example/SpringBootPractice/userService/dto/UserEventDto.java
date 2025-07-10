package com.example.SpringBootPractice.userService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO для представления события, связанного с пользователем.
 * Используется при отправке Kafka-сообщений.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEventDto {
    private String email;
    private String operation;
}
