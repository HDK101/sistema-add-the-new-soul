package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetCSV;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

import java.io.IOException;
import java.util.List;

public class ExportEmployeeCSVUseCase {
    private EmployeeCSV employeeCSV;
    private EmployeeDAO employeeDAO;

    public ExportEmployeeCSVUseCase(EmployeeCSV employeeCSV, EmployeeDAO employeeDAO) {
        this.employeeCSV = employeeCSV;
        this.employeeDAO = employeeDAO;
    }

    public void export(String fileName) throws IOException {
        List<Employee> employees = employeeDAO.findAll();
        employeeCSV.write(fileName, employees);
    }
}
