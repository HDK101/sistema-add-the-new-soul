package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
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

        Integer registrationNumber = Integer.valueOf(employee.getRegistrationNumber());
        if (employeeDAO.findById(registrationNumber).isPresent())
            throw new IllegalArgumentException("This asset id is already in use");

        return employeeDAO.add(employee);
    }
}
