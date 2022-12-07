package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

public class EvaluateAssetUseCase {
    private InventoryDAO inventoryDAO;
    private AssetDAO assetDAO;

    public EvaluateAssetUseCase(InventoryDAO inventoryDAO, AssetDAO assetDAO) {
        this.inventoryDAO = inventoryDAO;
        this.assetDAO = assetDAO;
    }

    private EvaluateResponse handleInventoryAsset(InventoryAsset inventoryAsset, Employee employee, String damage, LocationStatus locationStatus) {
        EvaluateResponse.EvaluateResponseBuilder evaluateResponseBuilder = EvaluateResponse.builder();

        if (damage != null) {
            inventoryAsset.applyDamage(damage);
            evaluateResponseBuilder.damageApplied(true);
        } else if (locationStatus != LocationStatus.CORRECT_LOCATION) {
            inventoryAsset.setLocationStatus(locationStatus);
        }
        inventoryAsset.setInventoryManager(employee);
        inventoryAsset.setStatus(Status.VERIFIED);

        evaluateResponseBuilder.currentLocationStatus(locationStatus);

        return evaluateResponseBuilder.build();
    }

    public EvaluateResponse evaluateAsset(EvaluateData evaluateData) {
        InventoryAsset inventoryAsset = evaluateData.getInventoryAsset().orElseThrow();
        Employee employee = evaluateData.getInventoryManager().orElseThrow();
        Inventory inventory = inventoryAsset.getInventory();

        LocationStatus locationStatus = evaluateData.getAssetLocationStatus();
        String damage = evaluateData.getDamage();

        inventoryAsset.applyEvaluation(damage, locationStatus);

        if (!inventory.hasEmployeeInCommision(employee))
            throw new IllegalStateException("Employee doesn't belong to the comission.");

        EvaluateResponse evaluateResponse = this.handleInventoryAsset(inventoryAsset, employee, evaluateData.getDamage(), evaluateData.getAssetLocationStatus());

        inventoryDAO.evaluateInventoryAsset(inventoryAsset);
        assetDAO.evaluateAsset(inventoryAsset.getAsset());

        return evaluateResponse;
    }
}
