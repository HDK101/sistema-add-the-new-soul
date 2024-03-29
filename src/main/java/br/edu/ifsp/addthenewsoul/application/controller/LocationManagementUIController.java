package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.ExportLocationCSVUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.FindLocationUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.ImportLocationCSVUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.RemoveLocationUseCase;
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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class LocationManagementUIController {

    public Button btnAdd;
    public Button btnEdit;
    public Button btnRemove;
    public Button btnImportCsv;
    public Button btnExportCsv;
    @FXML
    private TableView<Location> tableView;

    @FXML
    private TableColumn<Location, Integer> cNumber;

    @FXML
    private TableColumn<Location, String> cSection;

    @FXML
    private TableColumn<Location, Integer> cId;

    @FXML
    private TextField txtId;

    private ObservableList<Location> tableData;

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
        checkLoggedUserRole();
    }

    private void checkLoggedUserRole() {
        Employee employee = Session.getInstance().getLoggedUser();

        if (!employee.hasRole(Role.INVENTORY_MANAGER)) {
            btnAdd.setDisable(true);
            btnEdit.setDisable(true);
            btnRemove.setDisable(true);
            btnExportCsv.setDisable(true);
            btnImportCsv.setDisable(true);
        }
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        cSection.setCellValueFactory(new PropertyValueFactory<>("section"));
    }

    private void loadDataAndShow() {
        FindLocationUseCase findLocationUseCase = UseCases.getInstance().findLocationUseCase;
        List<Location> locations = findLocationUseCase.findAll();
        tableData.clear();
        tableData.addAll(locations);
    }

    @FXML
    void addLocation(ActionEvent event) throws IOException {
        WindowLoader.setRoot("LocationUI");
    }

    @FXML
    void editLocation(ActionEvent event) throws IOException {
        Location selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null)
            WindowLoader.setRoot("LocationUI");
            LocationUIController controller = (LocationUIController) WindowLoader.getController();
            controller.setLocation(selectedItem, UIMode.UPDATE);
    }

    @FXML
    void removeLocation(ActionEvent event) {
        Location selectedItem = tableView.getSelectionModel().getSelectedItem();
        RemoveLocationUseCase removeLocationUseCase = UseCases.getInstance().removeLocationUseCase;
        FindLocationUseCase findLocationUseCase = UseCases.getInstance().findLocationUseCase;


        if (selectedItem != null) {
            Location locationWithAssets = findLocationUseCase.findOne(selectedItem.getId()).orElseThrow();
            try {
                removeLocationUseCase.deleteLocation(locationWithAssets);
                loadDataAndShow();
            } catch (Exception e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Erro");
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        }
    }

    public void findLocationById(ActionEvent actionEvent) {
        FindLocationUseCase findLocationUseCase = UseCases.getInstance().findLocationUseCase;
        try {
            Integer id = Integer.valueOf(txtId.getText());
            Optional<Location> location = findLocationUseCase.findOne(id);
            if (location.isEmpty()) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Erro");
                errorAlert.setContentText("Localização não encontrada");
                errorAlert.showAndWait();
                return;
            }

            tableData.clear();
            tableData.add(location.get());
        } catch (NumberFormatException n) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Por favor, informe um id válido.");
            errorAlert.showAndWait();
        }   catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    public void findAllLocations(ActionEvent actionEvent) {
        loadDataAndShow();
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
            ImportLocationCSVUseCase importLocationCSVUseCase = UseCases.getInstance().importLocationCSVUseCase;
            importLocationCSVUseCase.importLocations(file.getAbsolutePath());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sucesso");
            alert.setContentText("O arquivo CSV foi importado");
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
            ExportLocationCSVUseCase exportLocationCSVUseCase = UseCases.getInstance().exportLocationCSVUseCase;
            exportLocationCSVUseCase.export(file.getAbsolutePath());
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


    public void createReport(ActionEvent actionEvent) {
        Location location = tableView.getSelectionModel().getSelectedItem();
        if (location == null) return;

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
            issueReportUseCase.issueLocationReport(file.getAbsolutePath(), location.getId());
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
