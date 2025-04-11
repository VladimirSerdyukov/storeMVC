package ru.storeMVC.storeMVC.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.storeMVC.storeMVC.models.Order;
import ru.storeMVC.storeMVC.models.User;
import ru.storeMVC.storeMVC.views.View;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserDto {

    @JsonView(View.Summary.class)
    private UUID uuid;

    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Summary.class)
    private String email;

    @JsonView(View.Details.class)
    private List<Order> orders;

    public UserDto(){}
    public UserDto(UUID id, String name, String email){
        this.uuid = id;
        this.name = name;
        this.email = email;
    }
    public UserDto(User user){
        this.email = user.getEmail();
        this.name = user.getName();
        this.uuid = user.getId();
        this.orders = user.getOrders();
    }

    public User getUser(){
        return new User(this.getUuid(), this.getEmail(),
                this.getName(), this.getOrders());
    }

    public static List<UserDto> allUser(List<User> list){

        List<UserDto> listDto = new ArrayList<>();
        listDto = list.stream().map(UserDto::new).toList();
        return listDto;
    }

}
