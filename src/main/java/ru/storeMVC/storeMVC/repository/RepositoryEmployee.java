package ru.storeMVC.storeMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.storeMVC.storeMVC.models.Employee;
import ru.storeMVC.storeMVC.models.EmployeeProjection;

import java.util.List;

public interface RepositoryEmployee extends JpaRepository<Employee, Integer> {

    Employee findEmployeeById(Integer id);

    Employee saveAndFlush(Employee employee);

    Employee save(Employee employee);

    void deleteById(Integer id);

    List<EmployeeProjection> getEmployeesByPosition(String position);
}