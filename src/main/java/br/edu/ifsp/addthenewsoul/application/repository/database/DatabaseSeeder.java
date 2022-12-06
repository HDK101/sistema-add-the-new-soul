package br.edu.ifsp.addthenewsoul.application.repository.database;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;

import java.util.EnumSet;

public class DatabaseSeeder {
    public static void main(String[] args) {
        Database.getConnection();
        seed();
    }

    public static void seed() {
        SQLiteEmployeeDAO employeeDAO = new SQLiteEmployeeDAO();

        String reg = employeeDAO.add(
                Employee.builder()
                        .name("Durandal")
                        .email("lucas@gmsail.com")
                        .hashPassword("$2y$10$y0PMpYhM8ZWOgcA4y1D3y.VTXAhrVllWQKT4YDNxVq4lFdlU1Nq1K")
                        .registrationNumber("REG123")
                        .roles(EnumSet.of(Role.EXECUTOR))
                        .build()
        );
        System.out.println(reg);
    }
}
