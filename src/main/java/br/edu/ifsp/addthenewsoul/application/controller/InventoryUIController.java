package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.EnumSet;

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
