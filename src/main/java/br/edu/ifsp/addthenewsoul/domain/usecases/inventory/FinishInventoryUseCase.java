package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;

public class FinishInventoryUseCase {

    private InventoryDAO inventoryDAO;

    public FinishInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public void finalizeInventory (Inventory inventory) {
        if (inventory.hasUnverifiedAssets())
            throw new IllegalArgumentException("Inventory can only be finalized if all goods are checked");
        inventory.finish();
        inventoryDAO.update(inventory);
    }
}
