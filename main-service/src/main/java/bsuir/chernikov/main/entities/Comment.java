package bsuir.chernikov.main.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "holder_id")
    private Courier holder;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
