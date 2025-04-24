package ru.storeMVC.storeMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.storeMVC.storeMVC.models.Department;

public interface RepositoryDepartment extends JpaRepository<Department, Integer> {
}
