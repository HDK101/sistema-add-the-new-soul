package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.EmployeePasswordHash;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;

public class UpdateEmployeeUseCase {
    private EmployeeDAO employeeDAO;

    public UpdateEmployeeUseCase(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public boolean update(Employee employee){
        ValidationOfEmployeeAttributes validator = new ValidationOfEmployeeAttributes();
        Notification notification = validator.isValid(employee);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        if (employee.getVirtualPassword() != null) EmployeePasswordHash.create(employee);

        String registrationNumber = employee.getRegistrationNumber();
        if(employeeDAO.findByRegistrationNumber(registrationNumber).isEmpty())
            throw new EntityNotFoundException("Employee not found.");

        return employeeDAO.update(employee);
    }

}
