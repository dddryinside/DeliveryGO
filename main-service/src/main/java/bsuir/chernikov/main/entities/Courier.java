package bsuir.chernikov.main.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Courier extends User {
    private String phone;
    private String email;
    private String location;
    private String about;

    @JsonIgnore
    @OneToMany(mappedBy = "courier")
    private List<Reply> replyList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "courier")
    private List<DeliveryOrder> orderList = new ArrayList<>();
}