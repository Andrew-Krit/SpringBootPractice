package com.example.controller;

import com.example.DefaultNotificationService;
import com.example.dto.UserEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для обработки уведомлений о событиях пользователей.
 * Получает запросы от клиента и делегирует отправку email-сообщений
 */
@RestController
@RequestMapping("/api/notify")
@RequiredArgsConstructor
public class NotificationController {
    private final DefaultNotificationService notificationService;

    /**
     * Обрабатывает POST-запрос на отправку email уведомления.
     * В зависимости от типа операции ("CREATED", "DELETED") генерирует соответствующее сообщение
     * и отправляет его на указанный email.
     */
    @PostMapping("/send")
    public ResponseEntity<Void> senEmail(@RequestBody UserEventDto request) {

        String message = switch (request.getOperation().toUpperCase()) {
            case "CREATED" -> "Здравствуйте! Ваш аккаунт был успешно создан.";
            case "DELETED" -> "Здравствуйте! Ваш аккаунт был удалён.";
            default -> "Неизвестная операция";
        };
        notificationService.sendEmail(request.getEmail(), "Ваш аккаунт", message);

        return ResponseEntity.ok().build();
    }
}
