package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.FindAssetUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.FindEmployeeUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class InventoryUIController {
    @FXML
    private Button btnCancelInventoryDetailed;

    @FXML
    private Button btnEditComissionInventoryDetailed;

    @FXML
    private Button btnEvaluateAssetInventoryDetailed;

    @FXML
    private Button btnSaveInventoryDetailed;

    @FXML
    private TableColumn<InventoryAsset, String> cDescriptionInventoryDetailed;

    @FXML
    private TableColumn<Employee, String> cEmailInventoryDetailed;

    @FXML
    private TableColumn<InventoryAsset, Employee> cEmployeeInChargeInventoryDetailed;

    @FXML
    private TableColumn<InventoryAsset, Integer> cIdAssetInventoryDetailed;

    @FXML
    private TableColumn<InventoryAsset, String> cLocalInventoryDetailed;

    @FXML
    private TableColumn<Employee, String> cNameInventoryDetailed;

    @FXML
    private TableColumn<Employee, String> cPhoneInventoryDetailed;

    @FXML
    private TableColumn<Employee, String> cRNInventoryDetailed;

    @FXML
    private TableColumn<Employee, EnumSet<Role>> cRolesInventoryDetailed;

    @FXML
    private TableColumn<InventoryAsset, String> cStatusInventoryDetailed;

    @FXML
    private ComboBox<Employee> cbComissionChiefInventoryDetailed;

    @FXML
    private DatePicker dpEndDataInventoryDetailed;

    @FXML
    private DatePicker dpInitialDateInventoryDetailed;

    @FXML
    private TableView<InventoryAsset> tableViewAssets;

    @FXML
    private TableView<Employee> tableViewComissionMembers;

    @FXML
    private TextField txtIdInventoryDetailed;

    @FXML
    private TextField txtNomeInventoryDetailed;

    private ObservableList<Employee> tableDataEmployees;
    private ObservableList<InventoryAsset> tableDataInventoryAssets;
    private List<Employee> employeesComission = new ArrayList<>();
    private List<Asset> assets = new ArrayList<>();

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        txtIdInventoryDetailed.setDisable(true);
        loadDataAndShow();
        FindEmployeeUseCase findEmployeeUseCase = UseCases.getInstance().findEmployeeUseCase;
        List<Employee> employees = findEmployeeUseCase.findAll();
        cbComissionChiefInventoryDetailed.getItems().setAll(employees);
    }

    private void bindTableViewToItemsList() {
        tableDataEmployees = FXCollections.observableArrayList();
        tableViewComissionMembers.setItems(tableDataEmployees);

        tableDataInventoryAssets = FXCollections.observableArrayList();
        tableViewAssets.setItems(tableDataInventoryAssets);
    }

    private void bindColumnsToValueSources() {
        cRNInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
        cNameInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("name"));
        cEmailInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("email"));
        cPhoneInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("phone"));

        cIdAssetInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("id"));
        cDescriptionInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("description"));
        cLocalInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("location"));
        cEmployeeInChargeInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("employeeInCharge"));
        cStatusInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    private void loadDataAndShow() {
        
    }

    @FXML
    void editComissionMembers(ActionEvent event) {

    }

    @FXML
    void evaluateAsset(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        WindowLoader.setRoot("InventoryManagementUI");
    }

    @FXML
    void saveInventory(ActionEvent event) throws IOException {
        WindowLoader.setRoot("InventoryManagementUI");
    }

}
