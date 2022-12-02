package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.EmployeePasswordHash;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ImportEmployeeCSVUseCase {
    private EmployeeCSV employeeCSV;
    private EmployeeDAO employeeDAO;

    public ImportEmployeeCSVUseCase(EmployeeCSV employeeCSV, EmployeeDAO employeeDAO) {
        this.employeeCSV = employeeCSV;
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> importEmployees(String fileName) throws IOException {
        List<Employee> employees = employeeCSV.read(fileName);
        employees.removeAll(employeeDAO.bulkAdd(employees).values());
        return employees;
    }
}
