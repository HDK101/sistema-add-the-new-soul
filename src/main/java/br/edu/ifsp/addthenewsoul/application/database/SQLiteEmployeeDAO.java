package br.edu.ifsp.addthenewsoul.application.database;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteEmployeeDAO implements EmployeeDAO {

    @Override
    public String add(Employee employee) {
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
    public boolean delete(String registrationNumber) {
        String sql = "DELETE FROM Employee WHERE registrationNumber = ?";

        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, registrationNumber);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Optional<Employee> findByRegistrationNumber(String registrationNumber) {
        String sql = "SELECT * FROM Employee where registrationNumber = ?";
        Employee employee = null;
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, registrationNumber);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                employee = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(employee);
    }

    private Employee resultSetToEntity(ResultSet rs) throws SQLException {
        Role role;
        if (rs.getString("role").equals("INVENTORY MANAGER"))
            role = Role.INVENTORY_MANAGER;
        else
            role = Role.EXECUTOR;

        return new Employee(
                            rs.getString("name"),
                            rs.getString("registrationNumber"),
                            rs.getString("hashPassword"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            role
        );
    }
}
