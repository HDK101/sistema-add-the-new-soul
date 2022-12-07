package br.edu.ifsp.addthenewsoul.application.controller;


import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.FindInventoryUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.FinishInventoryUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.StartInventoryUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InventoryStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class InventoryManagementUIController {
    @FXML
    private Button btnAllInventories;

    @FXML
    private Button btnCancelInventory;

    @FXML
    private Button btnDetailInventory;

    @FXML
    private Button btnFinishInventory;

    @FXML
    private Button btnOkInventory;

    @FXML
    private Button btnStartInventory;

    @FXML
    private TableColumn<Inventory, Employee> cChairmanInventory;

    @FXML
    private TableColumn<Inventory, List<Employee>> cComissionInventory;

    @FXML
    private TableColumn<Inventory, LocalDate> cEndDateInventory;

    @FXML
    private TableColumn<Inventory, Integer> cIdInventory;

    @FXML
    private TableColumn<Inventory, String> cNameInventory;

    @FXML
    private TableColumn<Inventory, LocalDate> cStartDateInventory;

    @FXML
    private TableColumn<Inventory, InventoryStatus> cStatusInventory;

    @FXML
    private TableView<Inventory> tableViewInventory;

    @FXML
    private TextField txtIdInventory;

    private ObservableList<Inventory> tableDataInventory;

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableDataInventory = FXCollections.observableArrayList();
        tableViewInventory.setItems(tableDataInventory);
    }

    private void bindColumnsToValueSources() {
        cIdInventory.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNameInventory.setCellValueFactory(new PropertyValueFactory<>("name"));
        cChairmanInventory.setCellValueFactory(new PropertyValueFactory<>("comissionPresident"));
        cStartDateInventory.setCellValueFactory(new PropertyValueFactory<>("initialDate"));
        cEndDateInventory.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        cStatusInventory.setCellValueFactory(new PropertyValueFactory<>("inventoryStatus"));
    }

    private void loadDataAndShow() {
        FindInventoryUseCase findInventoryUseCase = UseCases.getInstance().findInventoryUseCase;
        List<Inventory> inventories = findInventoryUseCase.findAll();
        System.out.println(inventories);
        tableDataInventory.clear();
        if(inventories != null)
            tableDataInventory.addAll(inventories);
    }

    @FXML
    void detailInventory(ActionEvent event) throws IOException {
        WindowLoader.setRoot("InventoryUI");
    }

    @FXML
    void findAllInventories(ActionEvent event) {
        loadDataAndShow();
    }

    @FXML
    void findInventoryById(ActionEvent event) {
        FindInventoryUseCase findInventoryUseCase = UseCases.getInstance().findInventoryUseCase;
        Optional<Inventory> inventory = findInventoryUseCase.findOne(txtIdInventory.getText());
        tableDataInventory.clear();
        tableDataInventory.add(inventory.orElseThrow());
    }

    @FXML
    void finishInventory(ActionEvent event) throws IOException {
        FinishInventoryUseCase finishInventoryUseCase = UseCases.getInstance().finishInventoryUseCase;
        Inventory inventory = tableViewInventory.getSelectionModel().getSelectedItem();
        finishInventoryUseCase.finalizeInventory(inventory, inventory.getComissionPresident());
        loadDataAndShow();
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        WindowLoader.setRoot("DashboardUI");
    }

    @FXML
    void startInventory(ActionEvent event) throws IOException {
        WindowLoader.setRoot("StartInventoryUI");
    }

    public void createReport(ActionEvent actionEvent) {

    }
}
