package ru.storeMVC.storeMVC.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.storeMVC.storeMVC.dto.UserDto;
import ru.storeMVC.storeMVC.exceptions.NoExistsUser;
import ru.storeMVC.storeMVC.models.Order;
import ru.storeMVC.storeMVC.models.User;
import ru.storeMVC.storeMVC.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceUser {

    private final UsersRepository repository;

    public ServiceUser(UsersRepository repository) {
        this.repository = repository;
    }

    public List<UserDto> allUser() {
        return repository.getAllUser();
    }

    public UserDto getUser(UUID uuid) throws NoExistsUser {
        return repository.getUserById(uuid);
    }

    public UserDto updateUser(UserDto user) {
        return repository.updateUser(user);
    }

    public void deleteUserById(UUID id){
        repository.deleteUser(id);
    }

    public void createUser(UserDto user) {
        UUID id = UUID.randomUUID();
        user.setUuid(id);
        if(user.getOrders() != null){
            for(Order o : user.getOrders()){
                o.setId_user(id);
            }
        }
        repository.createUser(user);
    }
}