package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

public interface EmployeeReportWriter {
    void write(Employee employee);
}
