package bsuir.chernikov.main.dto;

import lombok.Data;

@Data
public class ReplyResponse {
    private Long replyId;
    private Long courierId;
    private String courierName;
    private Integer courierRating;
    private Integer price;
}
