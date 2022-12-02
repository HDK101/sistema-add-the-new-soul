package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

import java.io.IOException;

public class EmployeeTXTReportWriter extends TXTWriter implements ReportWriter<Employee> {
    @Override
    public void write(String filename, Employee employee) throws IOException {
        reportBuilder = new StringBuilder();
        addDetail("Employee registration number: ", employee.getRegistrationNumber());
        addDetail("E-mail: ", employee.getEmail());
        addDetail("Phone number: ", employee.getPhone());
        addDetail("Role: ", employee.getRoles().toString());
        addSingleHead("Assets in charge: ");

        if (employee.getAssetsInCharge() != null) {
            for (Asset asset : employee.getAssetsInCharge()) {
                addAssetDetail("Asset ID: ", String.valueOf(asset.getId()));
                addAssetDetail("Description: ", asset.getDescription());
                addAssetDetail("Asset ID: ", String.valueOf(asset.getValue()));
                addAssetDetail("Damage: ", asset.getDamage());
                addAssetDetail("Location: ", asset.getLocation().toString());
            }
        }
        writeTxtFile();
    }
}
