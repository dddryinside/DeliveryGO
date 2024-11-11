package bsuir.chernikov.loggingservice.utils;

import bsuir.chernikov.loggingservice.entities.LogMessage;
import bsuir.chernikov.loggingservice.service.LogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReceiver {

    private final LogService logService;

    public RabbitMqReceiver(LogService logService) {
        this.logService = logService;
    }

    @RabbitListener(queues = "logging")
    public void receiveMessage(LogMessage message) {
        logService.saveLogMessage(message);
    }
}
