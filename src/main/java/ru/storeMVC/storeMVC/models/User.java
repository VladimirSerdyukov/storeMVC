package ru.storeMVC.storeMVC.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import ru.storeMVC.storeMVC.exceptions.NoExistsUser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
@Entity(name = "users")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column(name = "user_id")
    UUID id;

    @Column(name = "email")
    String email;

    @Column(name = "name")
    String name;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    private List<Order> orders = new ArrayList<>();


    public User() {
    }

    public User(User u) throws NoExistsUser {
        if (u != null) {
            this.id = u.getId();
            this.email = u.getEmail();
            this.name = u.getName();
        } else {
            throw new NoExistsUser();
        }
    }
}
