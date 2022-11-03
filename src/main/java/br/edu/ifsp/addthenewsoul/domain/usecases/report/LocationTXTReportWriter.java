package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.TXTWriter;

import java.io.IOException;

public class LocationTXTReportWriter extends TXTWriter implements ReportWriter<Location> {
    @Override
    public void write(Location location) throws IOException {
        addDetail("Location ID: ", location.getId());
        addDetail("Number: ", location.getNumber());
        addDetail("Section: ", location.getSection());

        for (Asset asset : location.getAssets()) {
            addAssetDetail("Asset ID: ", asset.getId());
            addAssetDetail("Description: ", asset.getDescription());
            addAssetDetail("Value: ", asset.getValue());
            addAssetDetail("Damage: ", asset.getDamage());
        }
    }
}
