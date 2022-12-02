package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.LoginEmployeeUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.InvalidCredentialsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginUIController {
    public TextField txtEmail;
    public Button btnLogin;
    public PasswordField txtPassword;

    public void login(ActionEvent actionEvent) {
        LoginEmployeeUseCase loginEmployeeUseCase = UseCases.getInstance().loginEmployeeUseCase;
        try {
            Employee employee = loginEmployeeUseCase.login(txtEmail.getText(), txtPassword.getText());

            System.out.println(employee);

            WindowLoader.setRoot("DashboardUI");
            // Ir pro dashboard: WindowLoader.setRoot("MainUI");
        } catch (InvalidCredentialsException | IllegalStateException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        } catch (NullPointerException | IOException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace();
        }
    }
}