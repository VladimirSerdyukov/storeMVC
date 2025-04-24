package ru.storeMVC.storeMVC.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="employee")
public class Employee implements EmployeeProjection{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="first_name")
    private String first_name;
    @Column(name="position")
    private String position;
    @Column(name="salary")
    private int salary;
    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @Override
    public String getFullName() {
        return this.first_name;
    }

    @Override
    public String getDepartmentName() {
        return department.getName();
    }
}
