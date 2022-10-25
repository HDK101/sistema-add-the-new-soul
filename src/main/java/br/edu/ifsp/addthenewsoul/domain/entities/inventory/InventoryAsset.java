package br.edu.ifsp.addthenewsoul.domain.entities.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;


public class InventoryAsset extends Asset {
    private Employee executor;

    public InventoryAsset(int id, String description, Employee employeeInCharge, double value, String damage, Employee executor) {
        super(id, description, employeeInCharge, value, damage);
        this.executor = executor;
    }

    public Employee getExecutor() {
        return executor;
    }
}
