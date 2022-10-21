package br.edu.ifsp.addthenewsoul.domain.entities.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.good.Good;


public class InventoryGood extends Good {
    private Employee executor;

    public InventoryGood(int id, String description, Employee employeeInCharge, double value, String damage, Employee executor) {
        super(id, description, employeeInCharge, value, damage);
        this.executor = executor;
    }

    public Employee getExecutor() {
        return executor;
    }
}
