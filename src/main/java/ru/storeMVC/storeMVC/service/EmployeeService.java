package ru.storeMVC.storeMVC.service;

import org.springframework.stereotype.Service;
import ru.storeMVC.storeMVC.models.Employee;
import ru.storeMVC.storeMVC.models.EmployeeProjection;
import ru.storeMVC.storeMVC.repository.RepositoryEmployee;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeService {

    private final RepositoryEmployee repository;
    public EmployeeService(RepositoryEmployee repository) {
        this.repository = repository;
    }

    public EmployeeProjection getEmployee(int id) throws SQLException {
        return repository.findEmployeeById(id);
    }

    public EmployeeProjection saveAndFlush(Employee employee) {
        return repository.saveAndFlush(employee);
    }

    public EmployeeProjection save(Employee employee) {
        return repository.save(employee);
    }

    public void delete(int id){
        repository.deleteById(id);
    }


    public List<EmployeeProjection> getEmployeeByPosition(String position){
        return repository.getEmployeesByPosition(position);
    }

}
