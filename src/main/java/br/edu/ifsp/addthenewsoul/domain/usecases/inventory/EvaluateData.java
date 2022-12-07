package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class EvaluateData {
    private Employee inventoryManager;
    private InventoryAsset inventoryAsset;
    private LocationStatus assetLocationStatus;
    private String damage;

    public Optional<InventoryAsset> getInventoryAsset() {
        return Optional.ofNullable(inventoryAsset);
    }

    public Optional<Employee> getInventoryManager() {
        return Optional.ofNullable(inventoryManager);
    }
}
