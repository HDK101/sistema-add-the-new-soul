package br.edu.ifsp.addthenewsoul.application.controller;


import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.FindInventoryUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.FinishInventoryUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InventoryStatus;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.IssueReportUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.addthenewsoul.domain.usecases.UseCases.*;

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
    public TableColumn<Inventory, List<Employee>> cCommission;

    public TableColumn<Inventory, List<InventoryAsset>> cAssets;

    @FXML
    private TableColumn<Inventory, LocalDate> cEndDateInventory;

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
        checkLoggedUserRole();
    }

    private void checkLoggedUserRole() {
        Employee employee = Session.refresh();

        assert employee != null;
        if (!employee.hasRole(Role.INVENTORY_MANAGER)) {
            btnStartInventory.setDisable(true);
            btnDetailInventory.setDisable(true);
        }
        if (!employee.hasRole(Role.CHAIRMAN_OF_THE_COMISSION)) {
            btnFinishInventory.setDisable(true);
        }
    }

    private void bindTableViewToItemsList() {
        tableDataInventory = FXCollections.observableArrayList();
        tableViewInventory.setItems(tableDataInventory);
    }

    private void bindColumnsToValueSources() {
        cNameInventory.setCellValueFactory(new PropertyValueFactory<>("name"));

        cChairmanInventory.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Inventory, Employee> call(TableColumn<Inventory, Employee> param) {
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

        cChairmanInventory.setCellValueFactory(new PropertyValueFactory<>("comissionPresident"));

        cStartDateInventory.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Inventory, LocalDate> call(TableColumn<Inventory, LocalDate> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) this.setText(null);
                        if (item != null) this.setText(item.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    }
                };
            }
        });
        cStartDateInventory.setCellValueFactory(new PropertyValueFactory<>("initialDate"));

        cEndDateInventory.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Inventory, LocalDate> call(TableColumn<Inventory, LocalDate> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) this.setText(null);
                        if (item != null) this.setText(item.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    }
                };
            }
        });

        cEndDateInventory.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        //cCommission.setCellValueFactory(new PropertyValueFactory<>("comission"));
        //cAssets.setCellValueFactory(new PropertyValueFactory<>("assets"));

        cStatusInventory.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Inventory, InventoryStatus> call(TableColumn<Inventory, InventoryStatus> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(InventoryStatus item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) this.setText(null);
                        if (item != null) this.setText(item.getDescription());
                    }
                };
            }
        });

        cStatusInventory.setCellValueFactory(new PropertyValueFactory<>("inventoryStatus"));
    }

    private void loadDataAndShow() {
        List<Inventory> inventories = getInstance().findInventoryUseCase.findAll();
        System.out.println(inventories);
        tableDataInventory.clear();
        if(inventories != null)
            tableDataInventory.addAll(inventories);
    }

    @FXML
    void detailInventory(ActionEvent event) throws IOException {
        Inventory inventory = tableViewInventory.getSelectionModel().getSelectedItem();
        if (inventory == null) return;

        WindowLoader.setRoot("InventoryUI");
        InventoryUIController inventoryUIController = (InventoryUIController) WindowLoader.getController();
        inventoryUIController.setSelectedInventory(inventory);
    }

    @FXML
    void findAllInventories(ActionEvent event) {
        loadDataAndShow();
    }

    @FXML
    void findInventoryById(ActionEvent event) {
        Optional<Inventory> inventory = getInstance().findInventoryUseCase.findOne(txtIdInventory.getText());
        tableDataInventory.clear();
        tableDataInventory.add(inventory.orElseThrow());
    }

    @FXML
    void finishInventory(ActionEvent event) throws IOException {
        Inventory inventory = tableViewInventory.getSelectionModel().getSelectedItem();

        System.out.println(tableViewInventory.getSelectionModel().getSelectedItem());

        if (inventory == null) return;

        Employee employee = Session.getInstance().getLoggedUser();

        FindInventoryUseCase findInventoryUseCase = UseCases.getInstance().findInventoryUseCase;
        FinishInventoryUseCase finishInventoryUseCase = UseCases.getInstance().finishInventoryUseCase;

        Inventory fullInventory = findInventoryUseCase.findOne(inventory.getId()).orElseThrow();

        if (!fullInventory.hasComissionPresident(employee)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Você não é o presidente da comissão");
            errorAlert.showAndWait();
            return;
        }


        finishInventoryUseCase.finalizeInventory(fullInventory);
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

    @FXML
    public void createReport(ActionEvent actionEvent) {
        Inventory inventory = tableViewInventory.getSelectionModel().getSelectedItem();
        if (inventory == null) return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Criar relatório");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Todos os arquivos", "*.*")
        );

        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        File file = fileChooser.showSaveDialog(stage);

        try {
            IssueReportUseCase issueReportUseCase = UseCases.getInstance().issueReportUseCase;
            issueReportUseCase.issueInventoryReport(file.getAbsolutePath(), inventory.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sucesso");
            alert.setContentText("O relatório em PDF foi criado");
            alert.showAndWait();
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Não foi possível criar o relatório em PDF");
            errorAlert.showAndWait();
            e.printStackTrace();
        }
    }
}
