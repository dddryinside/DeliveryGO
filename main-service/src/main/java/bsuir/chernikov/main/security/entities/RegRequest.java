package bsuir.chernikov.main.security.entities;

import lombok.Data;

@Data
public class RegRequest {
    private String name;
    private String username;
    private String password;
    private String role;
}
