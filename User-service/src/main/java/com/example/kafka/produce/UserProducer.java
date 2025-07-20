package com.example.kafka.produce;

import com.example.dto.UserEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Kafka producer, отвечающий за отправку событий пользователей в указанный топик.
 * Используется для передачи информации о создании или удалении.
 */
@Service
@RequiredArgsConstructor
public class UserProducer {
    private final KafkaTemplate<String, UserEventDto> kafkaTemplate;

    /**
     * Отправляет пользовательское событие в заданный Kafka-топик.
     */
    public void send(String topic, UserEventDto data) {
        kafkaTemplate.send(topic, data);
    }
}
