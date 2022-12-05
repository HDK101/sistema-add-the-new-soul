package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;

public class IssueReportUseCase {

    private final ReportWriter<Employee> employeeReportWriter;
    private final ReportWriter<Inventory> inventoryReportWriter;
    private final ReportWriter<Location> locationReportWriter;

    private final EmployeeDAO employeeDAO;
    private final InventoryDAO inventoryDAO;
    private final LocationDAO locationDAO;

    public IssueReportUseCase(ReportWriter<Employee> employeeReportWriter, ReportWriter<Inventory> inventoryReportWriter, ReportWriter<Location> locationReportWriter, EmployeeDAO employeeDAO, InventoryDAO inventoryDAO, LocationDAO locationDAO) {
        this.employeeReportWriter = employeeReportWriter;
        this.inventoryReportWriter = inventoryReportWriter;
        this.locationReportWriter = locationReportWriter;
        this.employeeDAO = employeeDAO;
        this.inventoryDAO = inventoryDAO;
        this.locationDAO = locationDAO;
    }

    public void issueInventoryReport(String filename, String inventoryId) {
        Inventory inventory = inventoryDAO.findInventoryById(inventoryId).orElseThrow();

        try {
            inventoryReportWriter.write(filename, inventory);
            System.out.println("Report issued.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Report not issued.");
        }
    }

    public void issueEmployeeReport(String filename, String registrationNumber) {
        try {
            Employee employee = employeeDAO.findByRegistrationNumber(registrationNumber).orElseThrow();

            employeeReportWriter.write(filename, employee);

            System.out.println("Employee report issued.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Employee report not issued.");
        }
    }

    public void issueLocationReport(String filename, Integer locationId) {
        Location location = locationDAO.findById(locationId).orElseThrow();

        try {
            locationReportWriter.write(filename, location);
            System.out.println("Report issued.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Report not issued.");
        }
    }
}
