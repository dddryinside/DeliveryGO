package bsuir.chernikov.main.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.mapping.Join;

import java.time.LocalDateTime;

@Data
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
