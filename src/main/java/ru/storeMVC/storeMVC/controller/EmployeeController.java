package ru.storeMVC.storeMVC.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.storeMVC.storeMVC.models.Employee;
import ru.storeMVC.storeMVC.models.EmployeeProjection;
import ru.storeMVC.storeMVC.service.EmployeeService;

import java.sql.SQLException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getEmployee(@PathVariable int id) throws SQLException {
        return ResponseEntity.ok(service.getEmployee(id).toString());
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        EmployeeProjection employeeProjection = service.save(employee);
        return ResponseEntity.ok(employeeProjection.toString());
    }

    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(service.saveAndFlush(employee).toString());
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("");
    }

}
