package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import static br.edu.ifsp.addthenewsoul.domain.usecases.UseCases.*;

public class AssetUIController {

    @FXML
    public ComboBox<Location> cbLocation;
    @FXML
    public Label lbId;
    @FXML
    private ComboBox<Employee> cbEmployeeInCharge;
    @FXML
    private TextField txtValue;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea txtDamages;
    @FXML
    private ComboBox<Status> cbStatus;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    private Asset asset;

    @FXML
    private void initialize(){
        cbLocation.getItems().setAll(findLocationUseCase.findAll());
        cbEmployeeInCharge.getItems().setAll(findEmployeeUseCase.findAll());
        cbStatus.setVisible(false);
        txtDamages.setVisible(false);
    }

    public void saveOrUpdate(ActionEvent actionEvent) {
        getEntityToView();
        if (asset.getId() == null)
            addAssetUseCase.add(asset);
        else
            updateAssetUseCase.updateAsset(asset);
    }

    private void getEntityToView(){
        if (asset == null) {
            asset = Asset.builder().build();
        }
        asset.setDescription(txtDescription.getText());
        asset.setEmployeeInCharge(cbEmployeeInCharge.getValue());
        asset.setValue(Double.parseDouble(txtValue.getText()));
        asset.setDamage(txtDamages.getText());
        asset.setStatus(cbStatus.getValue());
    }

    public void setBook(Asset asset, UIMode mode) {
        if(asset == null)
            throw new IllegalArgumentException("Book can not be null.");
        this.asset = asset;
        setEntityIntoView();

    }

    private void setEntityIntoView(){
        lbId.setText(String.valueOf(asset.getId()));
        cbStatus.setValue(asset.getStatus());
        cbLocation.setValue(asset.getLocation());
        txtValue.setText(String.valueOf(asset.getValue()));
        cbEmployeeInCharge.setValue(asset.getEmployeeInCharge());
        txtDescription.setText(asset.getDescription());
        txtDamages.setText(asset.getDamage());
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AssetManagementUI");
    }
}
