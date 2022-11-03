package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;

public class IssueReportUseCase {

    private ReportWriter<Employee> employeeReportWriter;
    private ReportWriter<Inventory> inventoryReportWriter;
    private ReportWriter<Location> locationReportWriter;

    private EmployeeDAO employeeDAO;
    private InventoryDAO inventoryDAO;
    private LocationDAO locationDAO;

    public IssueReportUseCase(ReportWriter<Employee> employeeReportWriter, ReportWriter<Inventory> inventoryReportWriter, ReportWriter<Location> locationReportWriter, EmployeeDAO employeeDAO, InventoryDAO inventoryDAO, LocationDAO locationDAO) {
        this.employeeReportWriter = employeeReportWriter;
        this.inventoryReportWriter = inventoryReportWriter;
        this.locationReportWriter = locationReportWriter;
        this.employeeDAO = employeeDAO;
        this.inventoryDAO = inventoryDAO;
        this.locationDAO = locationDAO;
    }

    public void issueInventoryReport(Integer inventoryId) {
        Inventory inventory = inventoryDAO.findInventoryById(inventoryId).orElseThrow();

        try {
            inventoryReportWriter.write(inventory);
            System.out.println("Report issued.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Report not issued.");
        }
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
        Location location = locationDAO.findById(locationId).orElseThrow();

        try {
            locationReportWriter.write(location);
            System.out.println("Report issued.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Report not issued.");
        }
    }
}
