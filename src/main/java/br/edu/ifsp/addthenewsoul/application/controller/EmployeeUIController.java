package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.AddEmployeeUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.FindEmployeeUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.UpdateEmployeeUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.EnumSet;

public class EmployeeUIController {
    @FXML
    private Button btnCancelEmployee;

    @FXML
    private Button btnSaveEmployee;

    @FXML
    private TextField txtEmailEmployee;

    @FXML
    private TextField txtNameEmployee;

    @FXML
    private TextField txtPasswordEmployee;

    @FXML
    private TextField txtPhoneEmployee;

    @FXML
    private TextField txtRegistrationNumber;

    @FXML
    private CheckBox ckExecutor;

    private Employee employee;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        WindowLoader.setRoot("DashboardUI");
    }

    @FXML
    void saveOrUpdateEmployee(ActionEvent event) throws IOException {
        getEntityFromView();
        FindEmployeeUseCase findEmployeeUseCase = UseCases.getInstance().findEmployeeUseCase;
        AddEmployeeUseCase addEmployeeUseCase = UseCases.getInstance().addEmployeeUseCase;
        UpdateEmployeeUseCase updateEmployeeUseCase = UseCases.getInstance().updateEmployeeUseCase;
        boolean newEmployee = findEmployeeUseCase.findOne(employee.getRegistrationNumber()).isEmpty();

        if (newEmployee) {
            addEmployeeUseCase.add(employee);
        } else {
            updateEmployeeUseCase.update(employee);
        }
        WindowLoader.setRoot("EmployeeManagementUI");
    }

    private void getEntityFromView() {
        setEmployeeRoles();
        employee.setRegistrationNumber(txtRegistrationNumber.getText());
        employee.setName(txtNameEmployee.getText());
        employee.setPhone(txtPhoneEmployee.getText());
        employee.setEmail(txtEmailEmployee.getText());
        employee.setVirtualPassword(txtPasswordEmployee.getText());

    }

    private void setEmployeeRoles() {
        if (ckExecutor.isSelected())
            employee.addRole(Role.EXECUTOR);
        else {
            employee.removeRole(Role.EXECUTOR);
        }
    }

    public void setEmployee(Employee employee, UIMode mode) {
        if (employee == null)
            throw new IllegalArgumentException("Employee can not be null.");

        this.employee = employee;
        setEntityIntoView();

        txtRegistrationNumber.setDisable(true);
    }

    public void createEmployee() {
        this.employee = Employee
                .builder()
                .roles(EnumSet.noneOf(Role.class))
                .build();
    }

    private void setEntityIntoView() {
        txtRegistrationNumber.setText(employee.getRegistrationNumber());
        txtNameEmployee.setText(employee.getName());
        txtPhoneEmployee.setText(employee.getPhone());
        txtEmailEmployee.setText(employee.getEmail());
        txtPasswordEmployee.setText(employee.getVirtualPassword());

        if (employee.hasRole(Role.EXECUTOR)) {
            ckExecutor.setSelected(true);
        }

        Employee loggedEmployee = Session.getInstance().getLoggedUser();
        ckExecutor.setDisable(loggedEmployee.equals(employee));
    }
}
