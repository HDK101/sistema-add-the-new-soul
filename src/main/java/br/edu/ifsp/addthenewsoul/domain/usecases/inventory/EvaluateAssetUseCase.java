package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

public class EvaluateAssetUseCase {
    private InventoryDAO inventoryDAO;

    public EvaluateAssetUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
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

        //Trocar para um do dominio proprio
        if (!inventory.hasEmployeeInCommision(employee))
            throw new IllegalStateException("Employee doesn't belong to the comission.");

        evaluateData.isValid();

        EvaluateResponse evaluateResponse = this.handleInventoryAsset(inventoryAsset, employee, evaluateData.getDamage(), evaluateData.getAssetLocationStatus());

        inventoryDAO.update(inventory);

        return evaluateResponse;
    }
}
