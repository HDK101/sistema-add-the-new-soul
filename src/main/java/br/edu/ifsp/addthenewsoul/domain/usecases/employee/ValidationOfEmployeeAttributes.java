package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

public class ValidationOfEmployeeAttributes extends Validator<Employee> {

    @Override
    public Notification isValid(Employee employee) {
        Notification notification = new Notification();

        if (employee == null) {
            notification.addError("Employee is null");
            return notification;
        }

        if (nullOrEmpty(employee.getName()))
            notification.addError("Name is null or empty");
        if (nullOrEmpty(employee.getRegistrationNumber()))
            notification.addError("Registration number is null or empty");
        if (nullOrEmpty(employee.getVirtualPassword()))
            notification.addError("Password is null or empty");
        if (nullOrEmpty(employee.getEmail()))
            notification.addError("E-mail is null or empty");
        if (nullOrEmpty(employee.getPhone()))
            notification.addError("Phone is null or empty");
        if (nullOrEmpty(employee.getRole().toString()))
            notification.addError("Role is null or empty");

        return notification;
    }

}


