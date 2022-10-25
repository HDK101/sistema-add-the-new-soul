package br.edu.ifsp.addthenewsoul.application.database;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteEmployeeDAO implements EmployeeDAO {
    @Override
    public Optional<Employee> findById(Integer registrationNumber) {
        String sql = "SELECT * FROM Employee where registrationNumber = ?";

        return Optional.empty();
    }

    @Override
    public Integer add(Employee employee) {
        String sql = "INSERT INTO Employee(name, registrationNumber, hashPassword, email, phone, role) VALUES (?, ?, ?, ?, ?, ?)";


        return null;
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM Employee";
        List<Employee> employees = new ArrayList<>();


        return null;
    }

    @Override
    public boolean update(Employee employee) {
        String sql = "UPDATE Employee SET name = ?, registrationNumber = ?, hashPassword = ?, email = ?, phone = ?, role = ?";


        return false;
    }

    @Override
    public boolean delete(Integer key) {
        String sql = "DELETE FROM Employee WHERE registrationNumber = ?";


        return false;
    }

}
