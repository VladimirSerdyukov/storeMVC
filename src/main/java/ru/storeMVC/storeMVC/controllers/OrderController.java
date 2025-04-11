package ru.storeMVC.storeMVC.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.storeMVC.storeMVC.models.Order;
import ru.storeMVC.storeMVC.services.ServiceOrder;

@RestController
@RequestMapping("/store")
public class OrderController {

    private final ServiceOrder service;

    public OrderController(ServiceOrder service) {
        this.service = service;
    }

    @GetMapping("order/all")
    public Page<Order> add(Pageable pageable) {
        return service.getAllOrders(pageable);
    }
}
