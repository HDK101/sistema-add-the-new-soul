package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.AddLocationUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LocationUIController {

    @FXML
    private Label lbId;

    @FXML
    private Label txtId;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtSection;

    private Location location;

    @FXML
    void saveOrUpdate(ActionEvent event) throws IOException {
        getEntityToView();

        try {
            if (location.getId() == null)
                UseCases.getInstance().addLocationUseCase.add(location);
            else
                UseCases.getInstance().updateLocationUseCase.update(location);
            WindowLoader.setRoot("LocationManagementUI");
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    private void getEntityToView(){
        if (location == null) {
            location = Location.builder().build();
        }
        location.setNumber(Integer.valueOf(txtNumber.getText()));
        location.setSection(txtSection.getText());

    }

    @FXML
    void backToPreviousScene(ActionEvent event) throws IOException {
        WindowLoader.setRoot("LocationManagementUI");
    }

    public void setLocation(Location location, UIMode mode) {
        if(mode == UIMode.UPDATE) {
            if (location == null)
                throw new IllegalArgumentException("Location can not be null.");

            this.location = location;
            setEntityIntoView();

            configureViewMode();
        }
    }

    private void setEntityIntoView(){
        txtId.setText(location.getId().toString());
        txtNumber.setText(location.getNumber().toString());
        txtSection.setText(location.getSection());
    }

    private void configureViewMode() {
        lbId.setVisible(true);
        txtId.setVisible(true);
    }

}
