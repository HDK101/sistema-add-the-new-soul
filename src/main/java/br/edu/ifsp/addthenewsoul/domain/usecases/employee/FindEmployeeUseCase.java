package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

import java.util.Optional;

public class FindEmployeeUseCase {

    private EmployeeDAO employeeDAO;

    public FindEmployeeUseCase(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Optional<Employee> findOne (String regNumber) {
        if (Validator.nullOrEmpty(regNumber))
            throw new IllegalArgumentException("Registration number can not be null or empty");
        return employeeDAO.findByRegistrationNumber(regNumber);
    }
}
