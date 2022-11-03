package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.TXTWriter;

import java.io.IOException;

public class InventoryTXTReportWriter extends TXTWriter implements InventoryReportWriter {
    private StringBuilder employeeReport;

    private void addDetail(String head, String content) {
        employeeReport
                .append(head).append(content).append("\n");
    }

    private void addAssetDetail(String head, String content) {
        employeeReport.append(" ")
                .append(head).append(content).append("\n");
    }

    @Override
    public void write(Inventory inventory) throws IOException {
        //Escrever o relatorio de inventario
        /*
        StringBuilder inventoryReport = new StringBuilder();
        StringBuilder inventoryAssetDetails = new StringBuilder();

        try {
            if (inventory.getEndDate() != null) {
                inventoryReport.append("Inventory ID: " + inventory.getId() + "\n" + "Name: " + inventory.getName() + "\n" +
                        "Started on: " + inventory.getInitialDate() + " Finished on: " + inventory.getEndDate() + "\n" +
                        "Comission president: " + inventory.getComissionPresident() + "\n" + "Comission members: " +
                        inventory.getComission() + "\n" + "Verified assets: " + "\n");
                for (InventoryAsset asset : inventory.getAssets()) {
                    inventoryAssetDetails.append("     -> Asset ID: " + asset.getId() + "Description: " +
                            asset.getDescription() + "Employee in charge: " + asset.getEmployeeInCharge() + "Value: " +
                            asset.getValue() + "Damage: " + asset.getDamage() + "Location: " + asset.getLocation() +
                            "\n" + "Status: " + asset.getStatus() + "\n");
                }

                writeTxtFile(inventoryReport.append(inventoryAssetDetails));
                System.out.println("Report issued.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Report not issued.");
        }
         */
    }
}
