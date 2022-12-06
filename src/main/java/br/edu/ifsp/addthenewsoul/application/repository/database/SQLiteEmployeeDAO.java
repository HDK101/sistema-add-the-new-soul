package br.edu.ifsp.addthenewsoul.application.repository.database;

import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToEmployee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SQLiteEmployeeDAO implements EmployeeDAO {
    class BulkAddResponse {
        public Employee employee;
        public boolean success;
    }

    @Override
    public Optional<Employee> findByRegistrationNumber(String registrationNumber) {
        String sql = """
                SELECT
                    e.registration_number AS e_registration_number,
                    e.name AS e_name,
                    e.phone as e_phone,
                    e.hash_password AS e_hash_password,
                    e.email AS e_email,
                    er.role AS er_role
                FROM Employee e
                LEFT JOIN EmployeeRole er
                WHERE e.registration_number = ?
                """;

        Employee employee = null;

        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, registrationNumber);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();

            if (resultSet.next()) {
                employee = ResultToEmployee.convert(resultSet);
                employee.addRole(Role.valueOf(resultSet.getString("er_role")));
                while (resultSet.next()) {
                    employee.addRole(Role.valueOf(resultSet.getString("er_role")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(employee);
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        String sql = """
                SELECT
                    e.registration_number AS e_registration_number,
                    e.name AS e_name,
                    e.phone as e_phone,
                    e.hash_password AS e_hash_password,
                    e.email AS e_email,
                    er.role AS er_role
                FROM Employee e
                LEFT JOIN EmployeeRole er ON e.registration_number = er.employee_reg
                WHERE e.email = ?
                """;

        Employee employee = null;

        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, email);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();

            while (resultSet.next()) {
                if (employee == null) employee = ResultToEmployee.convert(resultSet);
                employee.addRole(Role.valueOf(resultSet.getString("er_role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(employee);
    }

    public BulkAddResponse bulkAddItem(Employee employee) {
            String sql = """
                INSERT INTO Employee (
                    registration_number,
                    name,
                    email,
                    phone,
                    hash_password
                ) VALUES (
                    ?,
                    ?,
                    ?,
                    ?,
                    ?
                );
                """;
            try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
                stmt.setString(1, employee.getRegistrationNumber());
                stmt.setString(2, employee.getName());
                stmt.setString(3, employee.getEmail());
                stmt.setString(4, employee.getPhone());
                stmt.setString(5, employee.getHashPassword());

                BulkAddResponse bulkAddResponse = new BulkAddResponse();

                bulkAddResponse.employee = employee;
                bulkAddResponse.success = stmt.executeUpdate() == 1;
                return bulkAddResponse;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
    }

    @Override
    public Map<String, Employee> bulkAdd(List<Employee> items) {
        HashMap<String, Employee> employeeHashMap = new HashMap<>();

        List<Employee> employees = items.stream().filter(this::addEmployee).toList();
        System.out.println(employees);
        employees.forEach(employee -> {
            employeeHashMap.put(employee.getRegistrationNumber(), employee);
        });

        return employeeHashMap;
    }

    private boolean addRole(String registrationNumber, Role role, String sql) {
        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, registrationNumber);
            stmt.setString(2, role.toString());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void deleteRoles(String registrationNumber) {
        String sql = "DELETE FROM EmployeeRole WHERE employee_reg = ?";

        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, registrationNumber);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean putRoles(Employee employee) {
        this.deleteRoles(employee.getRegistrationNumber());

        List<Role> roles = employee.getRoles().stream().toList();
        String sql = """
                INSERT INTO EmployeeRole (
                    employee_reg,
                    role
                ) VALUES (
                    ?,
                    ?
                );
                """;

        for (Role role : roles) {
            boolean roleSuccess = this.addRole(employee.getRegistrationNumber(), role, sql);
            if (!roleSuccess) return false;
        }

        return true;
    }

    private boolean addEmployee(Employee employee) {
        String sql = """
                INSERT INTO Employee (
                    registration_number,
                    name,
                    email,
                    phone,
                    hash_password
                ) VALUES (
                    ?,
                    ?,
                    ?,
                    ?,
                    ?
                );
                """;
        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, employee.getRegistrationNumber());
            stmt.setString(2, employee.getName());
            stmt.setString(3, employee.getEmail());
            stmt.setString(4, employee.getPhone());
            stmt.setString(5, employee.getHashPassword());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String add(Employee employee) {
        Connection connection = Database.getConnection();

        try {
            connection.setAutoCommit(false);

            boolean employeeAddSuccess = this.addEmployee(employee);
            boolean employeeRolesPutSuccess = this.putRoles(employee);

            if (!employeeAddSuccess || !employeeRolesPutSuccess) {
                connection.rollback();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee.getRegistrationNumber();
    }

    @Override
    public boolean update(Employee employee) {
        String sql = """
                UPDATE Employee set
                    name = ?,
                    email = ?,
                    phone = ?,
                    hash_password = ?
                WHERE registration_number = ?
                """;
        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getEmail());
            stmt.setString(3, employee.getPhone());
            stmt.setString(4, employee.getHashPassword());
            stmt.setString(5, employee.getRegistrationNumber());
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String key) {
        String sql = "DELETE FROM Employee WHERE registration_number = ?";
        //Deletar Roles em cascata
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, key);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Employee> findAll() {
        String sql = """
                SELECT
                    e.registration_number AS e_registration_number,
                    e.name AS e_name,
                    e.phone as e_phone,
                    e.hash_password AS e_hash_password,
                    e.email AS e_email,
                    er.role AS er_role
                FROM Employee e
                LEFT JOIN EmployeeRole er
                WHERE e.registration_number = er.employee_reg
                """;
        Map<String, Employee> employeeMap = new HashMap<>();

        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();

            while (resultSet.next()) {
                String registrationNumber = resultSet.getString("e_registration_number");
                if (!employeeMap.containsKey(registrationNumber)) {
                    employeeMap.put(registrationNumber, ResultToEmployee.convert(resultSet));
                }
                Employee employee = employeeMap.get(registrationNumber);
                employee.addRole(Role.valueOf(resultSet.getString("er_role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeMap.values().stream().toList();
    }
}
