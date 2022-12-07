package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.ExportAssetCSVUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.FilterAssetsUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.FindAssetUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.ImportAssetCSVUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.FindEmployeeUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.FindLocationUseCase;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.addthenewsoul.domain.usecases.UseCases.*;

public class AssetManagementUIController {

    @FXML
    public RadioButton rbId;
    @FXML
    public RadioButton rbLocation;
    @FXML
    public RadioButton rbEmployeeInCharge;
    @FXML
    public RadioButton rbLocationAndEmployee;
    public ComboBox<Employee> cbEmployee;
    public ComboBox<Location> cbLocal;
    public Button btnAdd;
    public Button btnEdit;
    public Button btnRemove;
    public Button btnImportCsv;
    public Button btnExportCsv;
    @FXML
    private TableView<Asset> tableView;
    @FXML
    private TableColumn<Asset, Integer> cId;
    @FXML
    private TableColumn<Asset, String> cDescription;
    @FXML
    private TableColumn<Asset, Employee> cEmployeeInCharge;
    @FXML
    private TableColumn<Asset, Double> cValue;
    @FXML
    private TableColumn<Asset, String> cDamage;
    @FXML
    private TableColumn<Asset, Location> cLocation;
    @FXML
    private TextField txtFilter;

    private ObservableList<Asset> tableData;

    @FXML
    public void initialize () {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
        loadEmployeesAndLocations();
        checkLoggedUserRole();
    }

    private void checkLoggedUserRole() {
        Employee employee = Session.getInstance().getLoggedUser();

        if (!employee.hasRole(Role.EXECUTOR)) {
            btnImportCsv.setDisable(true);
            btnExportCsv.setDisable(true);
            btnAdd.setDisable(true);
            btnEdit.setDisable(true);
            btnRemove.setDisable(true);
        }
    }

    private void loadEmployeesAndLocations() {
        FindEmployeeUseCase findEmployeeUseCase = getInstance().findEmployeeUseCase;
        FindLocationUseCase findLocationUseCase = getInstance().findLocationUseCase;

        List<Employee> employees = new ArrayList<>();
        employees.add(null);
        employees.addAll(findEmployeeUseCase.findAll());

        List<Location> locations = new ArrayList<>();
        locations.add(null);
        locations.addAll(findLocationUseCase.findAll());

        this.cbEmployee.setItems(FXCollections.observableArrayList(employees));
        this.cbLocal.setItems(FXCollections.observableArrayList(locations));
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        cEmployeeInCharge.setCellValueFactory(new PropertyValueFactory<>("employeeInCharge"));
        cValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        cDamage.setCellValueFactory(new PropertyValueFactory<>("damage"));
        cLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    }

    private void loadDataAndShow() {
        List<Asset> assets = getInstance().findAssetUseCase.findAll();
        tableData.clear();
        tableData.addAll(assets);
    }

    public void importCSV(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importar CSV");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv"),
                new FileChooser.ExtensionFilter("Todos os arquivos", "*.*")
        );

        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);

        try {
            ImportAssetCSVUseCase importEmployeeCSVUseCase = UseCases.getInstance().importAssetCSVUseCase;
            List<Asset> assets = importEmployeeCSVUseCase.importAssets(file.getAbsolutePath());

            StringBuilder builder = new StringBuilder();

            builder.append("O arquivo CSV foi importado").append("\n\n");

            if (!assets.isEmpty()) {
                builder.append("Entretanto não foi possível importar os seguintes bens:").append("\n");
                assets.forEach(asset -> {
                    builder
                            .append(asset.getDescription())
                            .append("\n");
                });
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sucesso");
            alert.setContentText(builder.toString());
            loadDataAndShow();
            alert.showAndWait();
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Não foi possível carregar o arquivo CSV");
            errorAlert.showAndWait();
            e.printStackTrace();
        }
    }

    public void exportCSV(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exportar CSV");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv"),
                new FileChooser.ExtensionFilter("Todos os arquivos", "*.*")
        );

        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        File file = fileChooser.showSaveDialog(stage);

        try {
            ExportAssetCSVUseCase exportAssetCSVUseCase = UseCases.getInstance().exportAssetCSVUseCase;
            exportAssetCSVUseCase.export(file.getAbsolutePath());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sucesso");
            alert.setContentText("O arquivo CSV foi exportado");
            alert.showAndWait();
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Não foi possível exportar o arquivo CSV");
            errorAlert.showAndWait();
            e.printStackTrace();
        }
    }

    public void goBack(ActionEvent actionEvent) {
        try {
            WindowLoader.setRoot("DashboardUI");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void find(ActionEvent actionEvent) {
        FindAssetUseCase findAssetUseCase = getInstance().findAssetUseCase;
        Asset asset = findAssetUseCase.findOne(Integer.parseInt(txtFilter.getText())).orElseThrow();
        loadDataFilterShow(List.of(asset));
    }

    private void loadDataFilterShow(List<Asset> assets) {
        System.out.println(assets);
        tableData.clear();
        tableData.addAll(assets);
    }

    public void allAssets(ActionEvent actionEvent) {
        loadDataAndShow();
    }

    public void addAsset(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AssetUI");
    }

    public void editAsset(ActionEvent actionEvent) throws IOException {
        showBookInMode(UIMode.UPDATE);
    }

    public void removeAsset(ActionEvent actionEvent) {
        Asset selectedItem = tableView.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        if (selectedItem != null) {
            getInstance().removeAssetUseCase.removeAsset(selectedItem);
            loadDataAndShow();
        }
    }

    private void showBookInMode(UIMode mode) throws IOException {
        Asset selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            WindowLoader.setRoot("AssetUI");
            AssetUIController controller = (AssetUIController) WindowLoader.getController();
            controller.setBook(selectedItem, mode);
        }
    }

    public void filterByEmployeeLocation(ActionEvent actionEvent) {
        Location location = cbLocal.getValue();
        Employee employee = cbEmployee.getValue();

        FilterAssetsUseCase filterAssetsUseCase = getInstance().filterAssetsUseCase;

        if (location != null && employee != null) {
            loadDataFilterShow(filterAssetsUseCase.filterAssetsByLocationAndEmployee(location, employee));
        }
        else if (location != null) {
            loadDataFilterShow(filterAssetsUseCase.filterAssetsByLocal(location));

        }
        else if (employee != null) {
            loadDataFilterShow(filterAssetsUseCase.filterAssetsByEmployee(employee));
        }
        else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Você precisa informar, no mínimo, um funcionário ou um local");
            errorAlert.showAndWait();
        }
    }
}
