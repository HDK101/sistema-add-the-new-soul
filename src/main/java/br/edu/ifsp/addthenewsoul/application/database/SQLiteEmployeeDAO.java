package br.edu.ifsp.addthenewsoul.application.database;

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
    public Optional<Employee> findByRegistrationNumber(String registrationNumber) {
        String sql = "SELECT * FROM Employee where registrationNumber = ?";
        Employee employee = null;

        try(PreparedStatement stmt = Database.createPreparedStatement(sql)){
            stmt.setString(1, registrationNumber);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                employee = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(employee);

    }


    private Employee resultSetToEntity(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getString("name"),
                rs.getString("registrationNumber"),
                rs.getString("hashPassword"),
                rs.getString("email"),
                rs.getString("phone"),
                Role.toEnum(rs.getString("role"))
        );
    }


    @Override

    public String add(Employee employee) {
        String sql = "INSERT INTO Employee(name, registrationNumber, hashPassword, email, phone, role) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getRegistrationNumber());
            stmt.setString(3, employee.getHashPassword());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPhone());
            stmt.setString(6, employee.getRole().toString());
            stmt.execute();

            return employee.getRegistrationNumber();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM Employee";
        List<Employee> employees = new ArrayList<>();

        try(PreparedStatement stmt = Database.createPreparedStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Employee employee = resultSetToEntity(rs);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;


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


    
}
