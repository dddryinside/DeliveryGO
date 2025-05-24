package bsuir.chernikov.main.entities;

import bsuir.chernikov.main.enums.ROLE;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ROLE role;
    private String name;
    private String username;
    private String password;
    private Integer balance;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessageList;
}