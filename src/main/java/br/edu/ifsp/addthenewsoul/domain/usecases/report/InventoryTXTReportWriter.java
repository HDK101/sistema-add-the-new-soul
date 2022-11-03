package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.TXTWriter;

import java.io.IOException;

public class InventoryTXTReportWriter extends TXTWriter implements ReportWriter<Inventory> {
    @Override
    public void write(Inventory inventory) throws IOException {
        if (inventory.getEndDate() != null) {
            addDetail("Inventory ID:", inventory.getId());
            addDetail("Name:", inventory.getName());
            addDetail("Started on:", inventory.getInitialDate());
            addDetail("Finished on:", inventory.getEndDate());
            addDetail("Commision president:", inventory.getComissionPresident());
            addDetail("Commision members: ", inventory.getComission());
            addSingleHead("Verified assets: ");

            for (Asset asset : inventory.getAssets()) {
                addAssetDetail("Asset ID: ", asset.getId());
                addAssetDetail("Description: ", asset.getDescription());
                addAssetDetail("Employee in charge: ", asset.getEmployeeInCharge());
                addAssetDetail("Value: ", asset.getValue());
                addAssetDetail("Damage: ", asset.getDamage());
                addAssetDetail("Location: ", asset.getLocation());
                addAssetDetail("Status: ", asset.getStatus());
            }
        }
        writeTxtFile();
    }
}
