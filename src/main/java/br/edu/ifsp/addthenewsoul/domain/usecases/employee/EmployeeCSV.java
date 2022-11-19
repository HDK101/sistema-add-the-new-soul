package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.application.io.CSV;
import br.edu.ifsp.addthenewsoul.application.io.CSVNode;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeCSV extends CSV<Employee> {

    @Override
    public List<Employee> read(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner reader = new Scanner(file);
        List<Employee> employees = new ArrayList<>();

        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(",");

            Employee employee = Employee.builder()
                    .name(parts[0])
                    .registrationNumber(parts[1])
                    .hashPassword(parts[2])
                    .email(parts[3])
                    .phone(parts[4])
                    .role(Role.values()[Integer.parseInt(parts[5])]).build();

            employees.add(employee);
        }

        reader.close();

        return employees;
    }
}