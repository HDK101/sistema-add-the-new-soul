package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;

import java.io.IOException;
import java.util.List;

public class LocationPDFReportWriter extends PDFReportWriter implements ReportWriter<Location> {
    @Override
    public void write(String filename, Location location) throws IOException {
//        addDetail("Number: ", location.getNumber());
//        addDetail("Section: ", location.getSection());
//
//        for (Asset asset : location.getAssets()) {
//            addAssetDetail("Description: ", asset.getDescription());
//            addAssetDetail("Value: ", asset.getValue());
//            addAssetDetail("Damage: ", asset.getDamage());
//        }

        setFilename(filename);

        this.contentStart()
                .setFontSize(36)
                .setLeading(36)
                .addText(location.fullLocation());

        this.addNewLine()
                .setFontSize(36)
                .setLeading(36)
                .addText("Bens:");

        if (location.getAssets() != null) {
            for (Asset asset : location.getAssets()) {
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

                this
                        .addText("Status em relação ao local: " + asset.getLocationStatus().getName())
                        .addNewLine();
            }
        }

        contentEnd()
                .save();
    }
}
