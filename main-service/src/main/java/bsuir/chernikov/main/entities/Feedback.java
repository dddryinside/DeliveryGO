package bsuir.chernikov.main.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
