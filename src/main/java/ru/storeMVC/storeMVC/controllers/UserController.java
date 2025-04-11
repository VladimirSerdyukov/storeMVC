package ru.storeMVC.storeMVC.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.storeMVC.storeMVC.dto.UserDto;
import ru.storeMVC.storeMVC.exceptions.NoExistsUser;
import ru.storeMVC.storeMVC.services.ServiceUser;
import ru.storeMVC.storeMVC.views.View;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class UserController {
    private final ServiceUser serviceUser;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(ServiceUser serviceUser, ObjectMapper objectMapper) {
        this.serviceUser = serviceUser;
        this.objectMapper = objectMapper;
    }

    @GetMapping("user/summary/{id}")
    public ResponseEntity<String> getUserByIdSummary(@PathVariable UUID id) throws NoExistsUser, JsonProcessingException {
        return ResponseEntity.ok(objectMapper
                .writerWithView(View.Summary.class)
                .writeValueAsString(serviceUser.getUser(id)));
    }

    @GetMapping("user/details/{id}")
    public ResponseEntity<String> getUserByIdDetails(@PathVariable UUID id) throws NoExistsUser, JsonProcessingException {
        return ResponseEntity.ok(objectMapper
                .writerWithView(View.Details.class)
                .writeValueAsString(serviceUser.getUser(id)));
    }

    @GetMapping("user/all")
    public ResponseEntity<String> getAllUser() throws JsonProcessingException {
        return ResponseEntity.ok(objectMapper
                .writerWithView(View.Summary.class)
                .writeValueAsString(serviceUser.allUser()));
    }

    @PostMapping("user/create")
    public ResponseEntity<String> saveUser(@RequestBody UserDto request) {
        serviceUser.createUser(request);
        return ResponseEntity.ok("Пользователь сохранен");
    }

    @PatchMapping("user/update")
    public ResponseEntity<String> updateUser(@RequestBody UserDto user) throws JsonProcessingException {
        return ResponseEntity.ok(objectMapper.writeValueAsString(serviceUser.updateUser(user)));
    }

    @DeleteMapping("user/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        serviceUser.deleteUserById(id);
        return ResponseEntity.ok("Пользователь удален");
    }
}
