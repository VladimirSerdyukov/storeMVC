package ru.storeMVC.storeMVC.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;
import ru.storeMVC.storeMVC.views.View;

import java.util.UUID;

@Component
@Entity(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Order {
    @Id
    @Column(name = "order_id")
    @JsonView(View.Details.class)
    UUID id;

    @Column(name="id_user")
    @JsonView(View.Details.class)
    UUID id_user;

    @JsonView(View.Details.class)
    @Column(name = "product_name")
    String productName;

    @Column(name = "product_prise")
    @JsonView(View.Details.class)
    int price;

    @Column(name = "status")
    @JsonView(View.Details.class)
    String status;
}
