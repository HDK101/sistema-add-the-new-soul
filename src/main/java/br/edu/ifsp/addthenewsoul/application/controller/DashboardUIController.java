package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class DashboardUIController {
    public Label greetingsText;

    @FXML
    public void initialize() {
        greetingsText.setText("Olá, " + Session.getInstance().getLoggedUser().getName());
    }

    public void goToEmployees(ActionEvent actionEvent) {
        try {
            WindowLoader.setRoot("EmployeeManagementUI");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToAssets(ActionEvent actionEvent) {
        try {
            WindowLoader.setRoot("AssetManagementUI");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToLocations(ActionEvent actionEvent) {
        try {
            WindowLoader.setRoot("LocationManagementUI");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToInventories(ActionEvent actionEvent) {
        try {
            WindowLoader.setRoot("InventoryManagementUI");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToReports(ActionEvent actionEvent) {
        try {
            Session.logout();
            WindowLoader.setRoot("ReportManagementUI");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent) {
        try {
            Session.logout();
            WindowLoader.setRoot("LoginUI");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
