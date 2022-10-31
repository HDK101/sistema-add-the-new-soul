package br.edu.ifsp.addthenewsoul.application.repository.imMemory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;

import java.util.*;


public class InMemoryEmployeeDAO implements EmployeeDAO {

    private final Map<String, Employee> dbMemoryEmployee = new LinkedHashMap<>();


    @Override
    public Optional<Employee> findByRegistrationNumber(String registrationNumber) {
        if (dbMemoryEmployee.containsKey(registrationNumber))
            return Optional.of(dbMemoryEmployee.get(registrationNumber));
        return Optional.empty();
    }

    @Override
    public String add(Employee employee) {
        dbMemoryEmployee.put(employee.getRegistrationNumber(), employee);
        return employee.getRegistrationNumber();
    }

    @Override
    public boolean update(Employee employee) {
        if (dbMemoryEmployee.containsKey(employee.getRegistrationNumber())) {
            dbMemoryEmployee.replace(employee.getRegistrationNumber(), employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String registrationNumber) {
        if (dbMemoryEmployee.containsKey(registrationNumber)) {
            dbMemoryEmployee.remove(registrationNumber);
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(dbMemoryEmployee.values());
    }
}
