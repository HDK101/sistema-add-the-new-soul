package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.ExportAssetCSVUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.ImportAssetCSVUseCase;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static br.edu.ifsp.addthenewsoul.domain.usecases.UseCases.*;

public class AssetManagementUIController {

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
        List<Asset> assets = findAssetUseCase.findAll();
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
    }

    public void allAssets(ActionEvent actionEvent) {
    }

    public void addAsset(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AssetUI");
    }

    public void editAsset(ActionEvent actionEvent) throws IOException {
        showBookInMode(UIMode.UPDATE);
    }

    public void removeAsset(ActionEvent actionEvent) {
        Asset selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            removeAssetUseCase.removeAsset(selectedItem);
            loadDataAndShow();
        }
    }

    private void showBookInMode(UIMode mode) throws IOException {
        Asset selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            WindowLoader.setRoot("BookUI");
            AssetUIController controller = (AssetUIController) WindowLoader.getController();
            controller.setBook(selectedItem, mode);
        }
    }
}
