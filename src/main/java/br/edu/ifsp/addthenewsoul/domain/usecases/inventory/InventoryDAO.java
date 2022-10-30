package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;

import java.util.List;


public interface InventoryDAO{

    void insertRoleExecutor (Employee employee);

    void initializeInventory (Inventory inventory);

    boolean haveUnverifiedInventory (List<InventoryAsset> inventoryAssets);

    void removeRoleExecutor (List<Employee> employees);

    void finalizeInventory (Inventory inventory);
}
