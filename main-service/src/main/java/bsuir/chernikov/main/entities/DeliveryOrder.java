package bsuir.chernikov.main.entities;

import bsuir.chernikov.main.enums.CARGO_TYPE;
import bsuir.chernikov.main.enums.ORDER_STATUS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "delivery_order")
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private CARGO_TYPE category;

    @ManyToOne
    @JoinColumn(name = "start_address_id")
    private Address startPoint;

    @ManyToOne
    @JoinColumn(name = "end_address_id")
    private Address endPoint;

    private Double distance;
    private Double weight;
    private Double calculatedPrice;
    private Double calculatedTime;
    private Double co2Emission;

    private ORDER_STATUS status;
    private LocalDateTime created;
    private String country;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<Reply> replyList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
}