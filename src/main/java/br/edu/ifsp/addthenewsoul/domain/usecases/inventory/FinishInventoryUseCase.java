package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InventoryStatus;

public class FinishInventoryUseCase {

    private InventoryDAO inventoryDAO;

    public FinishInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public void finalizeInventory (Inventory inventory, Employee comissionPresident) {
        if (!inventory.getInventoryStatus().equals(InventoryStatus.OPENED))
            throw new IllegalArgumentException("There is no open inventory");
        if (inventory.hasUnverifiedAssets())
            throw new IllegalArgumentException("Inventory can only be finalized if all goods are checked");
        if (!comissionPresident.getRole().equals(Role.CHAIRMAN_OF_THE_COMISSION))
            throw new IllegalArgumentException("Only the chairman of the commission can close the inventory");
        inventory.finish();
        inventoryDAO.update(inventory);
    }
}
