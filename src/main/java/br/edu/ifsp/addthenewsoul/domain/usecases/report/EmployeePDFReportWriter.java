package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import java.io.IOException;
import java.util.List;

public class EmployeePDFReportWriter extends PDFReportWriter implements ReportWriter<Employee>{
    public void write(String filename, Employee employee) throws IOException {
        setFilename(filename);

        System.out.println(employee);

        this.contentStart()
                .setLeading(48)
                .setFontSize(36)
                .addText(employee.getName())

                .setFontSize(16)
                .setLeading(24)
                .addText("Registro do funcionário: " + employee.getRegistrationNumber())
                .addText("E-mail: " + employee.getEmail())
                .addText("Telefone: " + employee.getPhone());

        List<Role> roles = employee.getRoles().stream().toList();
        this.addText("Cargos:");
        for(Role role : roles) {
            this.addText("  " + role.getName());
        }

        this.addNewLine()
                .setFontSize(36)
                .addNewLine()
                .addText("Bens:");

        if (employee.getAssetsInCharge() != null) {
            for (Asset asset : employee.getAssetsInCharge()) {
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
                        .addText("Status em relação ao local: " + asset.getLocationStatus().toString())
                        .addNewLine();
            }
        }

        contentEnd()
                .save();
    }
}
