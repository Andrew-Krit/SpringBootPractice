package com.example;

/**
 * Контракт для сервисов, способных отправлять email-уведомления.
 */
public interface NotificationService {
    void sendEmail(String mailSendTo, String messageSubject, String MessageText);
}
