package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Hash;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.InvalidCredentialsException;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Session;

public class LoginEmployeeUseCase {
    private EmployeeDAO employeeDAO;

    public LoginEmployeeUseCase(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee login (String email, String password) {
        Employee employee = this.employeeDAO.findByEmail(email).orElseThrow();

        if (employee.getRoles().contains(Role.EMPLOYEE)) throw new IllegalStateException("Employee must not have a EMPLOYEE role");

        String hashPassword = employee.getHashPassword();
        BCrypt.Result result = Hash.verify(hashPassword, password);
        if (result.verified) {
            Session.login(employee);
            return employee;
        }
        throw new InvalidCredentialsException("Invalid credentials");
    }
}
