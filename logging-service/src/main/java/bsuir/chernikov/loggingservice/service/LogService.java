package bsuir.chernikov.loggingservice.service;

import bsuir.chernikov.loggingservice.entities.LogMessage;
import bsuir.chernikov.loggingservice.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    public LogMessage saveLogMessage(LogMessage logMessage) {
        return logRepository.save(logMessage);
    }
}
