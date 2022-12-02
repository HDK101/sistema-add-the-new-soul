package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.IssueReportUseCase;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class InventoryManagementUIController {
    public void createReport(ActionEvent actionEvent) {
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
            issueReportUseCase.issueInventoryReport(file.getAbsolutePath(), "123");
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
