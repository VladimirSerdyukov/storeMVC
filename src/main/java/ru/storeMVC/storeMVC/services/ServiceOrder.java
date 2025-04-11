package ru.storeMVC.storeMVC.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.storeMVC.storeMVC.models.Order;
import ru.storeMVC.storeMVC.repositories.OrdersRepository;

@Service
public class ServiceOrder {

    private final OrdersRepository repository;

    public ServiceOrder(OrdersRepository repository) {
        this.repository = repository;
    }

    public Page<Order> getAllOrders(Pageable pageable){
        return repository.findAll(pageable);
    }

}
