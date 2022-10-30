package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;

import java.util.List;

public class FinishInventoryUseCase {

    private InventoryDAO inventoryDAO;
    private Employee employee;

    public FinishInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public boolean checkStatusOfInventoryAssets (List<InventoryAsset> inventoryAssetList) {
        if (inventoryDAO.haveUnverifiedInventory(inventoryAssetList))
            throw new IllegalArgumentException("Inventory can only be finalized if all goods are checked");
        return true;
    }

    public void finalizeInventory (Inventory inventory
                                   ,List<InventoryAsset> inventoryAssets, List<Employee> employees) {
        if (employee.getRole().equals(Role.CHAIRMAN_OF_THE_COMISSION)) {
            if (inventoryDAO.haveUnverifiedInventory(inventoryAssets))
                throw new IllegalArgumentException("Inventory can only be finalized if all goods are checked");
            inventoryDAO.removeRoleExecutor(employees);
            inventoryDAO.finalizeInventory(inventory);
        }
    }
}
