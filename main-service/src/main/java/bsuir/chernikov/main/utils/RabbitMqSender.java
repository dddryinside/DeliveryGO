package bsuir.chernikov.main.utils;

import bsuir.chernikov.main.logging.LogMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitMqSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendLog(LogMessage logMessage) {
        amqpTemplate.convertAndSend("logging", logMessage);
    }
}