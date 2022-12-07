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
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.FindEmployeeUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.FindInventoryUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.StartInventoryUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.StartInventoryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private TableColumn<Asset, Location> cLocationAsset;

    @FXML
    private TableColumn<Asset, String> cDescriptionAsset;

    @FXML
    private TableColumn<Asset, Double> cValueAsset;

    @FXML
    private TableColumn<Asset, Employee> cEmployeeInChargeAsset;

    @FXML
    private DatePicker dpEndDateInventory;

    private ObservableList<Employee> tableDataEmployees;
    private ObservableList<Asset> tableDataAsset;
    private List<Employee> employeesComission = new ArrayList<>();
    private List<Asset> assets = new ArrayList<>();
    private String lblEmployees = "";
    private String lblAssets = "";

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
        FindEmployeeUseCase findEmployeeUseCase = UseCases.getInstance().findEmployeeUseCase;
        List<Employee> employees = findEmployeeUseCase.findAll();

        cbComissionChief.setConverter(new StringConverter<>() {
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

        cDescriptionAsset.setCellValueFactory(new PropertyValueFactory<>("description"));
        cValueAsset.setCellValueFactory(new PropertyValueFactory<>("value"));

        cEmployeeInChargeAsset.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Asset, Employee> call(TableColumn<Asset, Employee> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(Employee item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) this.setText(null);
                        if (item != null) this.setText(item.getName());
                    }
                };
            }
        });

        cEmployeeInChargeAsset.setCellValueFactory(new PropertyValueFactory<>("employeeInCharge"));

        cLocationAsset.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Asset, Location> call(TableColumn<Asset, Location> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(Location item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) this.setText(null);
                        if (item != null) this.setText(item.fullLocation());
                    }
                };
            }
        });
        cLocationAsset.setCellValueFactory(new PropertyValueFactory<>("location"));

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
        employeesComission.add(employee);
        listEmployeesInLabel();
    }

    @FXML
    void removeComissionMember(ActionEvent event) {
        Employee employee = tableViewEmployees.getSelectionModel().getSelectedItem();
        employeesComission.remove(employee);
        listEmployeesInLabel();
    }


    @FXML
    void addNewAssetToInventory(ActionEvent event) {
        StartInventoryUseCase startInventoryUseCase = UseCases.getInstance().startInventoryUseCase;
        Asset selectedAsset = tableViewAssets.getSelectionModel().getSelectedItem();

        assets.add(selectedAsset);

        listAssetsInLabel();
        startInventoryUseCase.createInventoryAssets(assets);
    }

    private void listAssetsInLabel() {
        lblInventoryAssets.setText(assets.stream().map(asset -> asset.getId().toString()).collect(Collectors.joining(", ")));
    }

    private void listEmployeesInLabel() {
        lblComissionMembers.setText(employeesComission.stream().map(Employee::getName).collect(Collectors.joining(", ")));
    }

    @FXML
    void removeAssetFromInventory(ActionEvent event) {
        Asset asset = tableViewAssets.getSelectionModel().getSelectedItem();
        assets.remove(asset);
        listAssetsInLabel();
    }



    @FXML
    void goBack(ActionEvent event) throws IOException {
        WindowLoader.setRoot("InventoryManagementUI");
    }



    @FXML
    void saveNewInventory(ActionEvent event) throws IOException {
        StartInventoryUseCase startInventoryUseCase = UseCases.getInstance().startInventoryUseCase;
        String nameInventory = txtNameInventory.getText();
        LocalDate initialDate = LocalDate.now();
        LocalDate endDate = dpEndDateInventory.getValue();
        Employee comissionChief = cbComissionChief.getValue();
        System.out.println(comissionChief);

        try {
            startInventoryUseCase.initializeInventory(nameInventory, initialDate, endDate, employeesComission, comissionChief, assets);
        } catch (StartInventoryException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            e.printStackTrace();
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Erro interno");
            errorAlert.showAndWait();
            e.printStackTrace();
        }
        WindowLoader.setRoot("InventoryManagementUI");
    }


}
















