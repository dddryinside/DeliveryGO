package bsuir.chernikov.main.requests;

import lombok.Data;

@Data
public class ReplyResponse {
    private Long replyId;
    private Long courierId;
    private String courierName;
    private Integer courierRating;
    private Integer price;
}
