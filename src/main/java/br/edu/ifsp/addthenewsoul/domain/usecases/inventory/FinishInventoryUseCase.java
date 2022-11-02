package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

import java.util.List;

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
