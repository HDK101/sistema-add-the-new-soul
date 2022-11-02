package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.EntityNotFoundException;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

public class IssueReportUseCase {

    private InventoryDAO inventoryDAO;
    private EmployeeDAO employeeDAO;
    private EmployeeReportWriter employeeReportWriter;
    private LocationDAO locationDAO;

    public IssueReportUseCase(InventoryDAO inventoryDAO, EmployeeDAO employeeDAO, LocationDAO locationDAO) {
        this.inventoryDAO = inventoryDAO;
        this.employeeDAO = employeeDAO;
        this.locationDAO = locationDAO;
    }

    public IssueReportUseCase(EmployeeDAO employeeDAO, EmployeeReportWriter employeeReportWriter) {
        this.employeeDAO = employeeDAO;
        this.employeeReportWriter = employeeReportWriter;
    }

    public void issueInventoryReport(Integer inventoryId) {
        /*
        Inventory inventory = inventoryDAO.findInventoryById(inventoryId).orElseThrow();
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

    public void issueEmployeeReport(String registrationNumber) {
        try {
            Employee employee = employeeDAO.findByRegistrationNumber(registrationNumber).orElseThrow();
            employeeReportWriter.write(employee);

            System.out.println("Employee report issued.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Employee report not issued.");
        }
    }

    public void issueLocationReport(Integer locationId) {
        /*
        Location location = locationDAO.findById(locationId).orElseThrow();
        StringBuilder locationReport = new StringBuilder();
        StringBuilder assetDetails = new StringBuilder();

        try {
            locationReport.append("Location ID: " + location.getId() + "\n" + "Number: " + location.getNumber() + "\n" +
                    "Section: " + location.getSection() + "\n" + "Assets in this location: " + "\n");
            for (Asset asset : location.getAssets()) {
                assetDetails.append("     -> Asset ID: " + asset.getId() + "Description: " +
                        asset.getDescription() + "Value: " + asset.getValue() + "Damage: " + asset.getDamage());
            }
            writeTxtFile(locationReport.append(assetDetails));
            System.out.println("Report issued.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Report not issued.");
        }
         */
    }
}
