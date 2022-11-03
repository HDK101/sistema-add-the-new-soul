package br.edu.ifsp.addthenewsoul.application.repository.inMemory;

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
    public Optional<Employee> findByEmail(String email) {
        Collection<Employee> employees = dbMemoryEmployee.values();

        List<Employee> employeesFiltered = employees.stream().filter(employee -> employee.getEmail().equals(email)).toList();

        return Optional.of(employeesFiltered.get(0));
    }

    @Override
    public String add(Employee employee) {
        dbMemoryEmployee.put(employee.getRegistrationNumber(), employee);
        return employee.getRegistrationNumber();
    }

    @Override
    public Map<String, Employee> bulkAdd(List<Employee> items) {
        Map<String, Employee> employees = new HashMap<>();

        items.stream().forEach(item -> {
            if (!employees.containsKey(item.getEmail())) {
                employees.put(item.getEmail(), item);
            }
        });

        dbMemoryEmployee.putAll(employees);

        return employees;
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
