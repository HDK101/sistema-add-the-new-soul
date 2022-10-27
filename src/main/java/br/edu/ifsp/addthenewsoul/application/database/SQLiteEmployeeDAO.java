package br.edu.ifsp.addthenewsoul.application.database;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;
import javafx.scene.chart.PieChart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteEmployeeDAO implements EmployeeDAO {

    @Override
    public Optional<Employee> findByRegistrationNumber(Integer registrationNumber) {
        String sql = "SELECT * FROM Employee where registrationNumber = ?";
        Employee employee = null;


    }

    @Override
    public Integer add(Employee employee) {
        String sql = "INSERT INTO Employee(name, registrationNumber, hashPassword, email, phone, role) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getRegistrationNumber());
            stmt.setString(3, employee.getHashPassword());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPhone());
            stmt.setString(6, employee.getRole().toString());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM Employee";
        List<Employee> employees = new ArrayList<>();

        
    }

    @Override
    public boolean update(Employee employee) {
        String sql = "UPDATE Employee SET name = ?, registrationNumber = ?, hashPassword = ?, email = ?, phone = ?, role = ?";

        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getRegistrationNumber());
            stmt.setString(3, employee.getHashPassword());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPhone());
            stmt.setString(6, employee.getRole().toString());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        String sql = "DELETE FROM Employee WHERE registrationNumber = ?";


        return false;
    }

}
