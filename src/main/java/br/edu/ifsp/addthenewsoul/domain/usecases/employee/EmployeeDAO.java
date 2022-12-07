package br.edu.ifsp.addthenewsoul.domain.usecases.employee;


import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;


import java.util.Optional;

public interface EmployeeDAO extends DAO<Employee, String> {
    Optional<Employee> findByRegistrationNumber(String  registrationNumber);
    Optional<Employee> findByEmail(String email);

    Employee findByName(String name);
}
