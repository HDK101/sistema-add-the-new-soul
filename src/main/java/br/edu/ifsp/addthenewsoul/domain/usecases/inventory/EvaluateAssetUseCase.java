package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

public class EvaluateAssetUseCase {

    private InventoryDAO inventoryDAO;

    public EvaluateAssetUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public void evaluateAsset(InventoryAsset asset, String damage){
        asset.applyDamage(damage);
        asset.setStatus(Status.VERIFIED);

        Inventory inventory = asset.getInventory();
        inventoryDAO.update(inventory);
    }
}
