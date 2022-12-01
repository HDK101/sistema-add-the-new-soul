package br.edu.ifsp.addthenewsoul.domain.usecases;

import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteEmployeeDAO;
import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteLocationDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.LoginEmployeeUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.*;

public class UseCases {
    private static UseCases instance;
    public EmployeeDAO employeeDAO;
    public LoginEmployeeUseCase loginEmployeeUseCase;
    public LocationDAO locationDAO;
    public AddLocationUseCase addLocationUseCase;
    public UpdateLocationUseCase updateLocationUseCase;
    public FindLocationUseCase findLocationUseCase;
    public RemoveLocationUseCase removeLocationUseCase;

    public UseCases() {
        employeeDAO = new SQLiteEmployeeDAO();
        loginEmployeeUseCase = new LoginEmployeeUseCase(employeeDAO);

        locationDAO = new SQLiteLocationDAO();
        addLocationUseCase = new AddLocationUseCase(locationDAO);
        updateLocationUseCase = new UpdateLocationUseCase(locationDAO);
        findLocationUseCase = new FindLocationUseCase(locationDAO);
        removeLocationUseCase = new RemoveLocationUseCase(locationDAO);

    }

    public static UseCases getInstance() {
        if (instance == null) instance = new UseCases();
        return instance;
    }

    public static void init() {
        if (instance == null) instance = new UseCases();
    }
}
