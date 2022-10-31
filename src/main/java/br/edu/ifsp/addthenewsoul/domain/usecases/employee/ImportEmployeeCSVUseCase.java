package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

import java.io.IOException;
import java.util.List;

public class ImportEmployeeCSVUseCase {
    private EmployeeCSV employeeCSV;
    private EmployeeDAO employeeDAO;

    public ImportEmployeeCSVUseCase(EmployeeCSV employeeCSV, EmployeeDAO employeeDAO) {
        this.employeeCSV = employeeCSV;
        this.employeeDAO = employeeDAO;
    }

    public void importEmployees(String fileName) throws IOException {
        List<Employee> employees = employeeCSV.read(fileName);
        employeeDAO.bulkAdd(employees);
    }
}
