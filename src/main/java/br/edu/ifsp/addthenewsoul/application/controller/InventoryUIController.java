package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.FindAssetUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.FindEmployeeUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.FindInventoryUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;

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
    private TableColumn<InventoryAsset, Status> cStatusInventoryDetailed;

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

    private Inventory selectedInventory;

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
        //cLocalInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("location"));
        //cEmployeeInChargeInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("employeeInCharge"));



        cStatusInventoryDetailed.setCellFactory(new Callback<>() {
            @Override
            public TableCell<InventoryAsset, Status> call(TableColumn<InventoryAsset, Status> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(Status item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) this.setText(null);
                        if (item != null) this.setText(item.getName());
                    }
                };
            }
        });
        cStatusInventoryDetailed.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    private void loadDataAndShow() {

    }

    @FXML
    void editComissionMembers(ActionEvent event) {

    }

    @FXML
    void evaluateAsset(ActionEvent event) throws IOException {
        InventoryAsset inventoryAsset = tableViewAssets.getSelectionModel().getSelectedItem();
        if (inventoryAsset != null) {
            WindowLoader.setRoot("AssetUI");
            AssetUIController controller = (AssetUIController) WindowLoader.getController();
            controller.setEvaluate(inventoryAsset, UIMode.EVALUATE);
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        WindowLoader.setRoot("InventoryManagementUI");
    }

    @FXML
    void saveInventory(ActionEvent event) throws IOException {
        WindowLoader.setRoot("InventoryManagementUI");
    }

    public void setSelectedInventory(Inventory selectedInventory) {
        FindInventoryUseCase findInventoryUseCase = UseCases.getInstance().findInventoryUseCase;

        Inventory inventoryFull = findInventoryUseCase.findOne(selectedInventory.getId()).orElseThrow();
        this.selectedInventory = inventoryFull;

        txtIdInventoryDetailed.setDisable(true);
        txtNomeInventoryDetailed.setDisable(true);
        dpInitialDateInventoryDetailed.setDisable(true);
        dpEndDataInventoryDetailed.setDisable(true);
        cbComissionChiefInventoryDetailed.setConverter(new StringConverter<>() {
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
        cbComissionChiefInventoryDetailed.setDisable(true);

        txtIdInventoryDetailed.setText(inventoryFull.getId());
        txtNomeInventoryDetailed.setText(inventoryFull.getName());
        dpInitialDateInventoryDetailed.setValue(inventoryFull.getInitialDate());
        dpEndDataInventoryDetailed.setValue(inventoryFull.getEndDate());
        cbComissionChiefInventoryDetailed.setValue(inventoryFull.getComissionPresident());

        tableViewAssets.setItems(FXCollections.observableArrayList(inventoryFull.getAssets()));
        tableViewComissionMembers.setItems(FXCollections.observableArrayList(inventoryFull.getComission()));
    }
}
