package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InventoryStatus;

public class FinishInventoryUseCase {

    private final InventoryDAO inventoryDAO;

    public FinishInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public void finalizeInventory (Inventory inventory, Employee comissionPresident) {
        if (inventory == null || comissionPresident == null) return;

        if (!inventory.getInventoryStatus().equals(InventoryStatus.OPEN))
            throw new IllegalArgumentException("There is no open inventory");
        if (inventory.hasUnverifiedAssets())
            throw new IllegalArgumentException("Inventory can only be finalized if all goods are checked");
        if (!comissionPresident.getRoles().contains(Role.CHAIRMAN_OF_THE_COMISSION))
            throw new IllegalArgumentException("Only the chairman of the commission can close the inventory");
        inventory.finish();
        inventoryDAO.update(inventory);
    }
}
