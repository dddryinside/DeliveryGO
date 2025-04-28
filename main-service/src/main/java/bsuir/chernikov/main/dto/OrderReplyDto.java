package bsuir.chernikov.main.dto;

import lombok.Data;

@Data
public class OrderReplyDto {
    private Integer orderId;
    private Integer price;
    private String replyMessage;

    private Integer replyId;
    private Integer courierId;
    private String courierName;
}