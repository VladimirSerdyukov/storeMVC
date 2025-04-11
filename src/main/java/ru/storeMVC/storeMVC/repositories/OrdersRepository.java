package ru.storeMVC.storeMVC.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.storeMVC.storeMVC.models.Order;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Order, UUID>, PagingAndSortingRepository<Order, UUID> {
}
