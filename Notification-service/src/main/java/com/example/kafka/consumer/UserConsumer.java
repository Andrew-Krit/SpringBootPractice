package com.example.kafka.consumer;

import com.example.DefaultNotificationService;
import com.example.dto.UserEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka consumer, отвечающий за обработку событий пользователей
 */
@Service
@RequiredArgsConstructor
public class UserConsumer {
    private final DefaultNotificationService notificationService;

    /**
     * Слушает Kafka-топик и обрабатывает входящие события пользователей.
     * На основе типа операции (например, CREATED или DELETED), отправляет уведомление на email, указанный в событии.
     */
    @KafkaListener(topics = "user-events", groupId = "user-group")
    public void listen(UserEventDto data) {
        String email = data.getEmail();
        String operation = data.getOperation();

        String message = switch (operation.toUpperCase()) {
            case "CREATED" -> "Здравствуйте! Ваш аккаунт был успешно создан.";
            case "DELETED" -> "Здравствуйте! Ваш аккаунт был удалён.";
            default -> "Неизвестная операция";
        };

        notificationService.sendEmail(email,"Ваш аккаунт",message);
    }
}
