package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.EmployeePasswordHash;

import java.io.IOException;
import java.util.List;

public class ImportEmployeeCSVUseCase {
    private EmployeeCSV employeeCSV;
    private EmployeeDAO employeeDAO;
    private EmployeePasswordHash employeePasswordHash;

    public ImportEmployeeCSVUseCase(EmployeeCSV employeeCSV, EmployeeDAO employeeDAO) {
        this.employeeCSV = employeeCSV;
        this.employeeDAO = employeeDAO;
    }

    public void importEmployees(String fileName) throws IOException {
        List<Employee> employees = employeeCSV.read(fileName);
        employeeDAO.bulkAdd(employees);
        System.out.println("Employees CSV file imported with success.");
    }
}
