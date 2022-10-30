package br.edu.ifsp.addthenewsoul.application.repository.imMemory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;

import java.util.List;

public class InMemoryInventoryDAO implements InventoryDAO {


    @Override
    public void insertRoleExecutor(Employee employee) {
        employee.setRole(Role.EXECUTOR);
    }

    @Override
    public void initializeInventory(Inventory inventory) {
        inventory.setassets(inventory.getassets());
        System.out.println("Created Inventory");
    }

    @Override
    public boolean haveUnverifiedInventory(List<InventoryAsset> inventoryAssets) {
        for (InventoryAsset inventoryAsset : inventoryAssets) {
            if (inventoryAsset.getStatus().equals(Status.VERIFIED))
                return false;
        }
        return true;
    }

    @Override
    public void removeRoleExecutor(List<Employee> employees) {
        for (Employee employee : employees) {
            employee.setRole(Role.EMPLOYEE);
        }
    }

    @Override
    public void finalizeInventory(Inventory inventory) {
        inventory.setassets(null);
    }
}
