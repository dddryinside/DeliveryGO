package bsuir.chernikov.main.dto;

import lombok.Data;

@Data
public class FeedbackDto {
    private int id;
    private String username;
    private String userRole;
    private String dateTime;
    private String message;
}