package bsuir.chernikov.main.logging;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogMessage {
    private String message;
    private String level;
    private String type;
    private LocalDateTime time;
}