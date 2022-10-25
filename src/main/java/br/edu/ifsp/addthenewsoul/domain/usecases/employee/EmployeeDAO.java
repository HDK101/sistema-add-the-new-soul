package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

import java.util.Optional;

public interface EmployeeDAO extends DAO<Employee, Integer> {
    Optional<Employee> findByRegistrationNumber(Integer registrationNumber);

}