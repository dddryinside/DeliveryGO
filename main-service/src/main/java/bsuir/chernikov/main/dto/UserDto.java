package bsuir.chernikov.main.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private String balance;
    private String role;
}
