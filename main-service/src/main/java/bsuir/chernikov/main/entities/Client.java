package bsuir.chernikov.main.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Client extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Address> addressList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<DeliveryOrder> orders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Comment> comments = new ArrayList<>();
}
