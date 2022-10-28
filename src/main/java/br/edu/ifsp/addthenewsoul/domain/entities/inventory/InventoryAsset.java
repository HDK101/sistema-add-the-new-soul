package br.edu.ifsp.addthenewsoul.domain.entities.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;


public class InventoryAsset extends Asset {
    private Status status;

    public InventoryAsset(int id, String description, Employee employeeInCharge, double value,
                          String damage, Location location, Status status) {

        super(id, description, employeeInCharge, value, damage, location);
        this.status = status;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

