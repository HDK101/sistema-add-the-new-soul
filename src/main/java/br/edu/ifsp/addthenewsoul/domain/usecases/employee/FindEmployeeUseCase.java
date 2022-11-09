package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.EntityNotFoundException;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindEmployeeUseCase {
    private EmployeeDAO employeeDAO;

    public FindEmployeeUseCase(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Optional<Employee> findOne (String  registrationNumber) {
        if (Validator.nullOrEmpty(registrationNumber))
            throw new EntityNotFoundException("Employee not found. Registration number cannot be null or empty.");
        return employeeDAO.findByRegistrationNumber(registrationNumber);
    }

    public List<Employee> findAll () {
        return employeeDAO.findAll();
    }
}
