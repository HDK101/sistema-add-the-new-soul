package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.*;
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

public class EmployeeManagementUIController {

    @FXML
    private Button btnAddNewEmployee;

    @FXML
    private Button btnAllEmployees;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnEditEmployee;

    @FXML
    private Button btnExportCsvEmployees;

    @FXML
    private Button btnImportCsvEmployees;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnRemoveEmployee;

    @FXML
    private TableColumn<Employee, String> cEmail;

    @FXML
    private TableColumn<Employee, String> cName;

    @FXML
    private TableColumn<Employee, String> cPhone;

    @FXML
    private TableColumn<Employee, String> cRegistrationNumber;

    @FXML
    private TableView<Employee> tableViewEmployee;

    @FXML
    private TextField txtFilterById;

    @FXML
    private TableColumn<Employee, String> cRoles;

    private ObservableList<Employee> tableDataEmployees;

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
            btnAddNewEmployee.setDisable(true);
            btnEditEmployee.setDisable(true);
            btnRemoveEmployee.setDisable(true);
            btnExportCsvEmployees.setDisable(true);
            btnImportCsvEmployees.setDisable(true);
        }
    }

    private void bindTableViewToItemsList() {
        tableDataEmployees = FXCollections.observableArrayList();
        tableViewEmployee.setItems(tableDataEmployees);
    }

    private void bindColumnsToValueSources() {
        cRegistrationNumber.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cRoles.setCellValueFactory(new PropertyValueFactory<>("rolesDescription"));
    }

    private void loadDataAndShow() {
        FindEmployeeUseCase findEmployeeUseCase = UseCases.getInstance().findEmployeeUseCase;
        List<Employee> employees = findEmployeeUseCase.findAll();
        tableDataEmployees.clear();
        if(employees != null)
            tableDataEmployees.addAll(employees);
    }

    @FXML
    void addNewEmployee(ActionEvent event) throws IOException {
        WindowLoader.setRoot("EmployeeUI");
        EmployeeUIController controller = (EmployeeUIController) WindowLoader.getController();
        controller.createEmployee();
    }

    @FXML
    void editEmployee(ActionEvent event) throws IOException {
        showUserInMode(UIMode.UPDATE);
    }

    private void showUserInMode(UIMode mode) throws IOException {
        Employee selectedEmployee = tableViewEmployee.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            WindowLoader.setRoot("EmployeeUI");
            EmployeeUIController controller = (EmployeeUIController) WindowLoader.getController();
            controller.setEmployee(selectedEmployee, mode);
        }
    }

    @FXML
    void removeEmployee(ActionEvent event) {
        RemoveEmployeeUseCase removeEmployeeUseCase = UseCases.getInstance().removeEmployeeUseCase;
        Employee selectedEmployee = tableViewEmployee.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            removeEmployeeUseCase.remove(selectedEmployee.getRegistrationNumber());
            loadDataAndShow();
        }
    }

    @FXML
    void findAllEmployees(ActionEvent event) {
        loadDataAndShow();
    }

    @FXML
    void findEmployeeById(ActionEvent event) {
        FindEmployeeUseCase findEmployeeUseCase = UseCases.getInstance().findEmployeeUseCase;
        Optional<Employee> employee = findEmployeeUseCase.findOne(txtFilterById.getText());
        tableDataEmployees.clear();
        tableDataEmployees.add(employee.orElseThrow());
    }

    public void importCSVEmployees(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importar CSV");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv"),
                new FileChooser.ExtensionFilter("Todos os arquivos", "*.*")
        );

        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);

        try {
            ImportEmployeeCSVUseCase importEmployeeCSVUseCase = UseCases.getInstance().importEmployeeCSVUseCase;
            List<Employee> employees = importEmployeeCSVUseCase.importEmployees(file.getAbsolutePath());

            StringBuilder builder = new StringBuilder();
            builder.append("O arquivo CSV foi importado").append("\n\n");

            if (!employees.isEmpty()) {
                builder.append("Entretanto não foi possível importar os seguintes funcionários:").append("\n");
                employees.forEach(employee -> {
                    builder
                            .append(employee.getRegistrationNumber())
                            .append(" ")
                            .append(employee.getEmail())
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

    public void exportCSVEmployees(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exportar CSV");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv"),
                new FileChooser.ExtensionFilter("Todos os arquivos", "*.*")
        );

        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        File file = fileChooser.showSaveDialog(stage);

        try {
            ExportEmployeeCSVUseCase exportEmployeeCSVUseCase = UseCases.getInstance().exportEmployeeCSVUseCase;
            exportEmployeeCSVUseCase.export(file.getAbsolutePath());
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

    public void goBack(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("DashboardUI");
    }

    public void createReport(ActionEvent actionEvent) {
        Employee employee = tableViewEmployee.getSelectionModel().getSelectedItem();
        if (employee == null) return;

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
            issueReportUseCase.issueEmployeeReport(file.getAbsolutePath(), employee.getRegistrationNumber());
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
