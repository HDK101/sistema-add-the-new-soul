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

        employeeDAO.add(
                Employee.builder()
                        .name("Lucas")
                        .email("lucas@gmail.com")
                        .hashPassword("$2y$10$ILZV4Wla1gELZ/Mj0xPDTe.kbmmEUzrIEQeL/V.eJc7v2YwSuZ2S6")
                        .phone("(18) 99999-9999")
                        .registrationNumber("REG123")
                        .roles(EnumSet.of(Role.EXECUTOR))
                        .build()
        );

        employeeDAO.add(
                Employee.builder()
                        .name("Vinicius")
                        .email("vinicius@gmail.com")
                        .hashPassword("$2y$10$NjQsT36YeV6TdeLLntUus.t.37KgOXVcgohrmSVIVRxGKFkV42zvC")
                        .phone("(16) 99111-1111")
                        .registrationNumber("REG456")
                        .roles(EnumSet.of(Role.EXECUTOR))
                        .build()
        );

        employeeDAO.add(
                Employee.builder()
                        .name("Isabela")
                        .email("isabela@gmail.com")
                        .hashPassword("$2y$10$CeErrZWHe0CmjL35InDs1OfuBahG/N.XkXSnBG0zcVnPbHhVPn3ba")
                        .phone("(16) 99222-2222")
                        .registrationNumber("REG789")
                        .roles(EnumSet.of(Role.EXECUTOR))
                        .build()
        );

        employeeDAO.add(
                Employee.builder()
                        .name("Caue")
                        .email("caue@gmail.com")
                        .hashPassword("$2y$10$PsNwhKinrm2SL5bvqlsQ6etCWCOxrXb8A/4rkw0QgMFR.T7Vqs7Yu")
                        .phone("(16) 99333-333")
                        .registrationNumber("REG980")
                        .roles(EnumSet.of(Role.EXECUTOR))
                        .build()
        );
    }
}
