package br.edu.ifsp.addthenewsoul.application.controller;

import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.usecases.UseCases;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.ExportAssetCSVUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.ImportAssetCSVUseCase;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AssetManagementUIController {
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
}
