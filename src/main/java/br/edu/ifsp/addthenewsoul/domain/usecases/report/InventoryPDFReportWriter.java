package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InventoryPDFReportWriter extends PDFReportWriter implements ReportWriter<Inventory> {
    @Override
    public void write(String filename, Inventory inventory) throws IOException {
        setFilename("pdf.pdf");

        this.contentStart()
                .setLeading(48)
                .setFontSize(36)
                .addText(inventory.getName())

                .setFontSize(16)
                .setLeading(24)
                .addText("Data de início: " + inventory.getInitialDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .addText("Data de fim: " + inventory.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .addText("Presidente da comissão: " + inventory.getComissionPresident().getName());

        this.addNewLine()
                .setFontSize(36)
                .addNewLine()
                .addText("Comissão:");

        if (inventory.getComission() != null) {
            for (Employee employee : inventory.getComission()) {
                this.setFontSize(18)
                        .setLeading(24)
                        .addText(employee.getName());
            }
        }

        this.addNewLine()
                .setFontSize(36)
                .addNewLine()
                .addText("Bens:");

        if (inventory.getAssets() != null) {
            for (InventoryAsset asset : inventory.getAssets()) {
                this.setFontSize(18)
                        .setLeading(24)
                        .addText(asset.getDescription())

                        .setFontSize(14)
                        .setLeading(18)
                        .addText("Valor: R$ " + String.format("%.2f", asset.getValue()))

                        .addText("Avarias: " + asset.getDamage());

                if (asset.getLocation() != null) {
                    this.addText("Local: " + asset.getLocation());
                }

                this.addNewLine();
            }
        }

        contentEnd()
                .save();
    }
}
