package br.edu.ifsp.addthenewsoul.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static br.edu.ifsp.addthenewsoul.application.Main.loginEmployeeUseCase;

public class LoginUIController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    public void login(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        try {
            loginEmployeeUseCase.login(email, password);
            //WindowLoader.setRoot("MainUI");
        } catch (Exception e) {
            showAlert("Erro", e.getMessage(), Alert.AlertType.ERROR);
        }

    }

     private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
