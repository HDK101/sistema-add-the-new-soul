package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

public class InventoryInvalidPresidentException extends RuntimeException{
    private Employee employee;

    public InventoryInvalidPresidentException(Employee employee, String message) {
        super(message);
        this.employee = employee;
    }
}