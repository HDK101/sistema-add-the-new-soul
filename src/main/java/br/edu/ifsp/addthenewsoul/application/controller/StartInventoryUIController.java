package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.FindAssetUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.FindEmployeeUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.FindInventoryUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.StartInventoryUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StartInventoryUIController {
    @FXML
    private Button btnAddAsset;

    @FXML
    private Button btnAddNewComissionMember;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRemoveComissionMember;

    @FXML
    private Button btnRemoveAsset;

    @FXML
    private Button btnSaveNewInventory;

    @FXML
    private TableColumn<Employee, String> cComissionMemberEmail;

    @FXML
    private TableColumn<Employee, String> cComissionMemberName;

    @FXML
    private TableColumn<Employee, String> cComissionMemberPhone;

    @FXML
    private TableColumn<Employee, String> cRegistrationNumber;

    @FXML
    private ComboBox<Employee> cbComissionChief;

    @FXML
    private Label lblComissionMembers;

    @FXML
    private Label lblInventoryAssets;

    @FXML
    private TableView<Employee> tableViewEmployees;

    @FXML
    private TableView<Asset> tableViewAssets;

    @FXML
    private TextField txtNameInventory;

    @FXML
    private TableColumn<Asset, Integer> cIdAsset;

    @FXML
    private TableColumn<Asset, Location> cLocationAsset;

    @FXML
    private TableColumn<Asset, String> cDescriptionAsset;

    @FXML
    private TableColumn<Asset, Double> cValueAsset;

    @FXML
    private TableColumn<Asset, Employee> cEmployeeInChargeAsset;

    private ObservableList<Employee> tableDataEmployees;
    private ObservableList<Asset> tableDataAsset;
    private List<Employee> employeesComission = new ArrayList<>();
    private List<Asset> assets = new ArrayList<>();
    private String lblEmployees = "";

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
        FindEmployeeUseCase findEmployeeUseCase = UseCases.getInstance().findEmployeeUseCase;
        List<Employee> employees = findEmployeeUseCase.findAll();
        cbComissionChief.getItems().setAll(employees);
    }

    private void bindTableViewToItemsList() {
        tableDataEmployees = FXCollections.observableArrayList();
        tableViewEmployees.setItems(tableDataEmployees);

        tableDataAsset = FXCollections.observableArrayList();
        tableViewAssets.setItems(tableDataAsset);
    }

    private void bindColumnsToValueSources() {
        cRegistrationNumber.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
        cComissionMemberName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cComissionMemberEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cComissionMemberPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        cIdAsset.setCellValueFactory(new PropertyValueFactory<>("id"));
        cDescriptionAsset.setCellValueFactory(new PropertyValueFactory<>("description"));
        //falta adicionar essa coluna de valor na tabela do sqlite de assets
        //cValueAsset.setCellValueFactory(new PropertyValueFactory<>("value"));
        cEmployeeInChargeAsset.setCellValueFactory(new PropertyValueFactory<>("employee_reg"));
        cLocationAsset.setCellValueFactory(new PropertyValueFactory<>("location_id"));

    }

    private void loadDataAndShow() {
        FindEmployeeUseCase findEmployeeUseCase = UseCases.getInstance().findEmployeeUseCase;
        List<Employee> employees = findEmployeeUseCase.findAll();
        tableDataEmployees.clear();
        if (employees != null)
            tableDataEmployees.addAll(employees);

        FindAssetUseCase findAssetUseCase = UseCases.getInstance().findAssetUseCase;
        List<Asset> assets = findAssetUseCase.findAll();
        tableDataAsset.clear();
        if (employees != null)
            tableDataAsset.addAll(assets);
    }

    public void addNewComissionMember(ActionEvent actionEvent) {
        Employee employee = tableViewEmployees.getSelectionModel().getSelectedItem();
        lblEmployees += employee.getName() + ", ";
        lblComissionMembers.setText(lblEmployees);
    }

    @FXML
    void removeComissionMember(ActionEvent event) {
        Employee employee = tableViewEmployees.getSelectionModel().getSelectedItem();
        employeesComission.remove(employee);
        String employeeName = employee.getName();
        List<String> newLblEmployees = List.of(lblEmployees.split(", "));
        System.out.println(newLblEmployees);
        //CORRIGIR
        /*
        for(String piece : newLblEmployees){
            if(piece.equals(employeeName))
                newLblEmployees.replace(employeeName, "");

        }*/
    }


    @FXML
    void addNewAssetToInventory(ActionEvent event) {
        StartInventoryUseCase startInventoryUseCase = UseCases.getInstance().startInventoryUseCase;
        Asset asset = tableViewAssets.getSelectionModel().getSelectedItem();
        assets.add(asset);
        startInventoryUseCase.createInventoryAssets(assets);
        lblInventoryAssets.setText(String.valueOf(asset.getId()));
    }


    @FXML
    void removeAssetFromInventory(ActionEvent event) {
        StartInventoryUseCase startInventoryUseCase = UseCases.getInstance().startInventoryUseCase;

    }



    @FXML
    void goBack(ActionEvent event) throws IOException {
        WindowLoader.setRoot("InventoryManagementUI");
    }



    @FXML
    void saveNewInventory(ActionEvent event) {
        StartInventoryUseCase startInventoryUseCase = UseCases.getInstance().startInventoryUseCase;
        String nameInventory = txtNameInventory.getText();
        LocalDate initialDate = LocalDate.now();
        LocalDate endDate = null;
        Employee comissionChief = cbComissionChief.getValue();

        startInventoryUseCase.initializeInventory(nameInventory, initialDate, endDate, employeesComission, comissionChief, assets);
    }


}
















