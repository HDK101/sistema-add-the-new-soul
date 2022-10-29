package br.edu.ifsp.addthenewsoul.application.repository.imMemory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;

public class InMemoryInventoryDAO implements InventoryDAO {


    @Override
    public void updateRole(Employee employee) {
        employee.setRole(Role.EXECUTOR);
    }

    @Override
    public void initializeInventory(Inventory inventory) {
        inventory.setassets(inventory.getassets());
        System.out.println("Created Inventory");
    }
}
