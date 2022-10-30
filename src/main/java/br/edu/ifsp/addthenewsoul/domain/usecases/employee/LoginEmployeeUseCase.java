package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.AccessLevel;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Hash;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InvalidCredentialsException;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Session;

public class LoginEmployeeUseCase {
    private EmployeeDAO employeeDAO;

    public LoginEmployeeUseCase(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void login (String email, String password) {
        Employee employee = this.employeeDAO.findByEmail(email).orElseThrow();
        String hashPassword = employee.getHashPassword();
        BCrypt.Result result = Hash.verify(hashPassword, password);
        if (result.verified) {
            Session.login(employee);
            return;
        }
        throw new InvalidCredentialsException("Invalid credentials");
    }
}
