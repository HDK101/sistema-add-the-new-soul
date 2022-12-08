package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

public class DashboardUIController {
    public Label greetingsText;

    @FXML
    private Label lblWarning;

    @FXML
    public void initialize() {

        greetingsText.setText("Olá, " + Session.getInstance().getLoggedUser().getName());

        if(Session.getInstance().getLoggedUser().getRoles().contains(Role.EXECUTOR)){
            List<Asset> assetsInCharge = Session.getInstance().getLoggedUser().getAssetsInCharge();
            for(Asset asset : assetsInCharge){
                if(asset.getLocationStatus().equals(LocationStatus.LOST)){
                    lblWarning.setText("ATENÇÃO! Você é responsável por bens que se encontram atualmente perdidos! Favor verificar.");
                }
            }
        }


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
