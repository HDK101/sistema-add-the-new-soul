package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

public class EvaluateAssetUseCase {

    private InventoryDAO inventoryDAO;

    public EvaluateAssetUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public void evaluateAsset(Employee employee, InventoryAsset asset, String damage){
        Inventory inventory = asset.getInventory();

        if (!inventory.getComission().contains(employee)) throw new IllegalArgumentException("Employee doesn't belong to the comission.");

        asset.applyDamage(damage);
        asset.setStatus(Status.VERIFIED);

        asset.setResponsible(employee);

        inventoryDAO.update(inventory);
    }
}
