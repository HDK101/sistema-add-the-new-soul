package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.FindAssetUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryAssetsDetailedUIController {
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnEvaluateInventoryAssets;

    @FXML
    private Button btnFindAll;

    @FXML
    private Button btnOkFilter;

    @FXML
    private TableColumn<InventoryAsset, String> cDamageInventoryAssets;

    @FXML
    private TableColumn<InventoryAsset, Employee> cEvaluatorInventoryAssets;

    @FXML
    private TableColumn<InventoryAsset, Integer> cIdInventoryAssets;

    @FXML
    private TableColumn<InventoryAsset, Location> cLocationInventoryAssets;

    @FXML
    private TableColumn<InventoryAsset, String> cDescriptionInventoryAssets;

    @FXML
    private TableColumn<InventoryAsset, Status> cStatusInventoryAssets;

    @FXML
    private RadioButton rdEvaluatorInventoryAsset;

    @FXML
    private RadioButton rdIdInventoryAssets;

    @FXML
    private RadioButton rdInventoryAssets;

    @FXML
    private TextField txtFilterInventoryAssets;

    @FXML
    private TableView<Asset> tableViewInventoryAssets;

    private ObservableList<Asset> tableDataInventoryAsset;

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableDataInventoryAsset = FXCollections.observableArrayList();
        tableViewInventoryAssets.setItems(tableDataInventoryAsset);
    }

    private void bindColumnsToValueSources() {
        cIdInventoryAssets.setCellValueFactory(new PropertyValueFactory<>("id"));
        cDescriptionInventoryAssets.setCellValueFactory(new PropertyValueFactory<>("description"));
        cLocationInventoryAssets.setCellValueFactory(new PropertyValueFactory<>("location"));
        cEvaluatorInventoryAssets.setCellValueFactory(new PropertyValueFactory<>("inventoryManager"));
        cDamageInventoryAssets.setCellValueFactory(new PropertyValueFactory<>("damage"));
        cStatusInventoryAssets.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadDataAndShow() {
        FindAssetUseCase findAssetUseCase = UseCases.getInstance().findAssetUseCase;
        List<Asset> assets = findAssetUseCase.findAll();
        tableDataInventoryAsset.clear();
        if(assets != null)
            tableDataInventoryAsset.addAll(assets);
    }


    @FXML
    void evaluateInventoryAssets(ActionEvent event) throws IOException {
        InventoryAsset inventoryAsset = tableViewInventoryAssets.getSelectionModel().getSelectedItem().getInventoryAsset();
        System.out.println(inventoryAsset);
        if (inventoryAsset != null) {
            WindowLoader.setRoot("AssetUI");
            AssetUIController controller = (AssetUIController) WindowLoader.getController();
            controller.setBook(inventoryAsset.getAsset(), UIMode.EVALUATE);
        }

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        WindowLoader.setRoot("InventoryUI");
    }

    @FXML
    void filterInventoryAssets(ActionEvent event) {
        FindAssetUseCase findAssetUseCase = UseCases.getInstance().findAssetUseCase;
        if(rdIdInventoryAssets.isSelected()){
            Asset inventoryAsset = findAssetUseCase.findOne(Integer.valueOf(txtFilterInventoryAssets.getText())).orElseThrow();
            tableDataInventoryAsset.clear();
            if(inventoryAsset != null)
                tableDataInventoryAsset.add(inventoryAsset);
        }
        if(rdEvaluatorInventoryAsset.isSelected()){

        }
        if(rdInventoryAssets.isSelected()){
            List<Asset> inventoryAssets = findAssetUseCase.findAll();
            List<Asset> damagedInventoryAssets = null;
            for(Asset a : inventoryAssets){
                if(a.getDamage() != null){
                    damagedInventoryAssets.add(a);
                }
            }
            tableDataInventoryAsset.clear();
            tableDataInventoryAsset.addAll(damagedInventoryAssets);
        }

    }

    @FXML
    void findAllInventoryAssets(ActionEvent event) {
        FindAssetUseCase findAssetUseCase = UseCases.getInstance().findAssetUseCase;
        List<Asset> inventoryAssets = findAssetUseCase.findAll();
        tableDataInventoryAsset.clear();
        if(inventoryAssets != null)
            tableDataInventoryAsset.addAll(inventoryAssets);
    }
}
