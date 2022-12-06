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
                        .name("Vinicius")
                        .email("vinicius@gmail.com")
                        .hashPassword("$2y$10$NjQsT36YeV6TdeLLntUus.t.37KgOXVcgohrmSVIVRxGKFkV42zvC")
                        .registrationNumber("REG456")
                        .roles(EnumSet.of(Role.EXECUTOR))
                        .build()
        );
        System.out.println(reg);
    }
}
