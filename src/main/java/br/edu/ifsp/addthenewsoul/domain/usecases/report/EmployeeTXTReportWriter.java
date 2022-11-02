package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;

public class EmployeeTXTReportWriter extends TXTWriter implements EmployeeReportWriter {
    private StringBuilder employeeReport;

    public EmployeeTXTReportWriter() {
        this.employeeReport = new StringBuilder();
    }

    private void addDetail(String head, String content) {
        employeeReport
                .append(head).append(content).append("\n");
    }

    private void addAssetDetail(String head, String content) {
        employeeReport.append(" ")
                .append(head).append(content).append("\n");
    }

    @Override
    public void write(Employee employee) {

        try {
            addDetail("Employee registration number: ", employee.getRegistrationNumber());
            addDetail("E-mail: ", employee.getEmail());
            addDetail("Phone number: ", employee.getPhone());
            addDetail("Role: ", employee.getRole().toString());
            employeeReport.append("Assets in charge: ").append('\n');

            if (employee.getAssetsInCharge() != null) {
                for (Asset asset : employee.getAssetsInCharge()) {
                    addAssetDetail("Asset ID: ", String.valueOf(asset.getId()));
                    addAssetDetail("Description: ", asset.getDescription());
                    addAssetDetail("Asset ID: ", String.valueOf(asset.getValue()));
                    addAssetDetail("Damage: ", asset.getDamage());
                    addAssetDetail("Location: ", asset.getLocation().toString());
                }
            }
            writeTxtFile(employeeReport);
            employeeReport = new StringBuilder();
            System.out.println("Report issued.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Report not issued.");
        }
    }
}
