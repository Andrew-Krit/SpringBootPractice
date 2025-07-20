package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Реализация сервиса отправки уведомлений.
 */
@Service
@RequiredArgsConstructor
public class DefaultNotificationService implements NotificationService {
    private final JavaMailSender mailSender;

    /**
     * Отправляет текстовое письмо на указанный email.
     */
    @Override
    public void sendEmail(String mailSendTo, String messageSubject, String messageText) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailSendTo);
        message.setSubject(messageSubject);
        message.setText(messageText);

        mailSender.send(message);
    }
}
