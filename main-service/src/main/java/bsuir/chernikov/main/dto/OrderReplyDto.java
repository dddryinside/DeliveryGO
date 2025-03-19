package bsuir.chernikov.main.dto;

import lombok.Data;

@Data
public class OrderReplyDto {
    private Integer replyId;
    private Integer courierId;
    private Integer orderId;
    private String courierName;
    private Integer price;
    private String coverLetter;
}