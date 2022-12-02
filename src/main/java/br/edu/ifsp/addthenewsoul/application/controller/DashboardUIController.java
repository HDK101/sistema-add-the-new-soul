package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class DashboardUIController {
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
}
