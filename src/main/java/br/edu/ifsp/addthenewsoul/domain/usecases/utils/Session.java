package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.FindEmployeeUseCase;

import java.util.Optional;

public class Session {

    private static Session instance;
    private AccessLevel accessLevel;

    private Employee loggedUser;

    private Session() {}

    public static synchronized Session getInstance() {
        if (instance == null)
            instance = new Session();
        return instance;
    }

    public static void logout () {
        Session session = Session.getInstance();
        session.setLoggedUser(null);
        session.accessLevel = AccessLevel.DENIED;
    }

    public static void login (Employee user) {
        Session session = Session.getInstance();
        session.setLoggedUser(user);
        session.accessLevel = AccessLevel.ALLOWED;
    }

    public static Employee refresh() {
        Session session = Session.getInstance();
        Employee loggedEmployee = session.getLoggedUser();
        FindEmployeeUseCase findEmployeeUseCase = UseCases.getInstance().findEmployeeUseCase;

        if (session.getLoggedUser() != null) {
            Employee employee = findEmployeeUseCase.findOne(loggedEmployee.getRegistrationNumber()).orElseThrow();
            session.setLoggedUser(findEmployeeUseCase.findOne(loggedEmployee.getRegistrationNumber()).orElseThrow());
            return employee;
        }
        return null;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Employee getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Employee loggedUser) {
        this.loggedUser = loggedUser;
    }
}
