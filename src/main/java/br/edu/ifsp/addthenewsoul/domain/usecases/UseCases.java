package br.edu.ifsp.addthenewsoul.domain.usecases;

import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteEmployeeDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.LoginEmployeeUseCase;

public class UseCases {
    private static UseCases instance;
    public EmployeeDAO employeeDAO;
    public LoginEmployeeUseCase loginEmployeeUseCase;

    public UseCases() {
        employeeDAO = new SQLiteEmployeeDAO();
        loginEmployeeUseCase = new LoginEmployeeUseCase(employeeDAO);
    }

    public static UseCases getInstance() {
        if (instance == null) instance = new UseCases();
        return instance;
    }

    public static void init() {
        if (instance == null) instance = new UseCases();
    }
}
