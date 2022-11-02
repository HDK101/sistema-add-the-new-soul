package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

import java.io.IOException;

public interface EmployeeReportWriter {
    void write(Employee employee) throws IOException;
}
