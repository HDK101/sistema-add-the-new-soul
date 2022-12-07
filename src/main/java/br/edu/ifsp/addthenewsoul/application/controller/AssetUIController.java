package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.EvaluateData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.io.IOException;

import static br.edu.ifsp.addthenewsoul.domain.usecases.UseCases.*;

public class AssetUIController {

    @FXML
    public ComboBox<Location> cbLocation;
    @FXML
    public Label lbId;
    @FXML
    public Label lbStatus;
    @FXML
    public Button btnEvaluate;
    @FXML
    public Label lbLocationStatus;
    @FXML
    public ComboBox<LocationStatus> cbLocationStatus;
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

    private InventoryAsset inventoryAsset;

    @FXML
    private void initialize(){
        cbLocation.setConverter(new StringConverter<>() {
            @Override
            public String toString(Location object) {
                if (object == null) return null;
                return object.fullLocation();
            }

            @Override
            public Location fromString(String string) {
                return null;
            }
        });
        cbLocation.getItems().setAll(getInstance().findLocationUseCase.findAll());

        cbEmployeeInCharge.setConverter(new StringConverter<>() {
            @Override
            public String toString(Employee object) {
                if (object == null) return null;
                return object.getName();
            }

            @Override
            public Employee fromString(String string) {
                return null;
            }
        });

        cbEmployeeInCharge.getItems().setAll(getInstance().findEmployeeUseCase.findAll());

        cbStatus.setConverter(new StringConverter<>() {
            @Override
            public String toString(Status object) {
                if (object == null) return null;
                return null;
            }

            @Override
            public Status fromString(String string) {
                return null;
            }
        });
        cbStatus.getItems().setAll(Status.values());
        cbLocationStatus.getItems().setAll(LocationStatus.values());
        cbStatus.setValue(Status.NOT_VERIFIED);
        btnCancel.setVisible(true);
        btnSave.setVisible(true);
        cbStatus.setDisable(true);
        txtDamages.setVisible(false);
        lbStatus.setVisible(false);
        btnEvaluate.setVisible(false);
        txtDescription.setDisable(false);
        txtValue.setDisable(false);
        cbLocation.setDisable(false);
        cbEmployeeInCharge.setDisable(false);
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if (asset.getId() == null)
            getInstance().addAssetUseCase.add(asset);
        else
            getInstance().updateAssetUseCase.updateAsset(asset);
        WindowLoader.setRoot("AssetManagementUI");
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
        asset.setLocation(cbLocation.getValue());
    }

    public void setBook(Asset asset, UIMode mode) {
        if(asset == null)
            throw new IllegalArgumentException("Asset can not be null.");
        this.asset = asset;
        setEntityIntoView();

        if (mode == UIMode.UPDATE)
            configureViewModeUpdate();
        else if (mode == UIMode.EVALUATE);
            configureViewModeEvaluate();
    }

    public void setEvaluate(InventoryAsset inventoryAsset, UIMode mode) {
        if(inventoryAsset == null)
            throw new IllegalArgumentException("Asset can not be null.");
        this.inventoryAsset = inventoryAsset;
        setEntityIntoEvaluate();

        if (mode == UIMode.EVALUATE)
            configureViewModeEvaluate();
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

    private void setEntityIntoEvaluate(){
        lbId.setText(String.valueOf(inventoryAsset.getAsset().getId()));
        cbStatus.setValue(inventoryAsset.getAsset().getStatus());
        cbLocation.setValue(inventoryAsset.getAsset().getLocation());
        txtValue.setText(String.valueOf(inventoryAsset.getAsset().getValue()));
        cbEmployeeInCharge.setValue(inventoryAsset.getAsset().getEmployeeInCharge());
        txtDescription.setText(inventoryAsset.getAsset().getDescription());
        txtDamages.setText(inventoryAsset.getAsset().getDamage());
    }

    private void configureViewModeUpdate() {
        cbStatus.setDisable(false);
        txtDamages.setVisible(true);
        lbStatus.setVisible(true);
        btnSave.setVisible(true);
        btnCancel.setVisible(true);
    }

    private void configureViewModeEvaluate() {
        cbStatus.setDisable(false);
        txtDamages.setVisible(true);
        lbStatus.setVisible(true);
        btnCancel.setVisible(false);
        btnSave.setVisible(false);
        txtDescription.setDisable(true);
        txtValue.setDisable(true);
        cbLocation.setDisable(true);
        cbEmployeeInCharge.setDisable(true);
        btnEvaluate.setVisible(true);
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AssetManagementUI");
    }

    public void evaluateAsset(ActionEvent actionEvent) throws IOException {
        System.out.println(inventoryAsset);

        getInstance().evaluateAssetUseCase.evaluateAsset(EvaluateData.builder()
                .damage(txtDamages.getText())
                .assetLocationStatus(cbLocationStatus.getValue())
                .inventoryManager(inventoryAsset.getInventoryManager())
                .inventoryAsset(inventoryAsset)
                .build());

        WindowLoader.setRoot("InventoryUI");
        InventoryUIController inventoryUIController = (InventoryUIController) WindowLoader.getController();
        inventoryUIController.setSelectedInventory(inventoryAsset.getInventory());
    }
}
