package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;

public class AddEmployeeUseCase {
    private EmployeeDAO employeeDAO;

    public AddEmployeeUseCase(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Integer save (Employee employee) {
        ValidationOfEmployeeAttributes validator = new ValidationOfEmployeeAttributes();
        Notification notification = validator.isValid(employee);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String registrationNumber = employee.getRegistrationNumber();
        if (employeeDAO.findByRegistrationNumber(registrationNumber).isPresent())
            throw new EntityAlreadyExistsException("This registration number is already in use.");

        return employeeDAO.add(employee);
    }
}
