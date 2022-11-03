package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

public class EmployeePasswordHash {
    public static void create(Employee employee) {
        String virtualPassword = employee.getVirtualPassword();
        String passwordHash = Hash.hash(virtualPassword, 12);
        employee.setVirtualPassword(null);
        employee.setHashPassword(passwordHash);
    }
}
