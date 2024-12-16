package bsuir.chernikov.main.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Integer id;
    private String text;
    private Integer holderId;
    private String authorUsername;
    private Integer authorId;
    private String dateTime;
    private Boolean deletePermission;
}