package ru.storeMVC.storeMVC.repository;

import org.springframework.stereotype.Repository;
import ru.storeMVC.storeMVC.models.Department;
import ru.storeMVC.storeMVC.models.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class EmployeeRepository {


    private final DataSource dataSource;

    public EmployeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM employee WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultEmployee = statement.executeQuery();
        Employee employee = new Employee();
        while (resultEmployee.next()) {
            employee.setId(resultEmployee.getInt("id"));
            employee.setFirst_name(resultEmployee.getString("first_name"));
            employee.setPosition(resultEmployee.getString("position"));
            employee.setSalary(resultEmployee.getInt("salary"));
            query = "SELECT * FROM department WHERE id = ?";
            PreparedStatement statementDepartment = connection.prepareStatement(query);
            statementDepartment.setInt(1, resultEmployee.getInt("department"));
            ResultSet resultDepartment = statementDepartment.executeQuery();
            while (resultDepartment.next()) {
                employee.setDepartment(new Department(resultDepartment.getInt("id"), resultDepartment.getString("name")));
            }
            resultDepartment.close();
            statementDepartment.close();
        }

        resultEmployee.close();
        statement.close();
        connection.close();
        return employee;
    }
}
