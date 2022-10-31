package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;


public interface InventoryDAO extends DAO<Inventory, Integer> {

    Optional<Inventory> findInventoryById(Integer id);

    void insertRoleExecutor (Employee employee);

    void initializeInventory (Inventory inventory);

    boolean haveUnverifiedInventory (List<InventoryAsset> inventoryAssets);

    void removeRoleExecutor (List<Employee> employees);

    void finalizeInventory (Inventory inventory);

    List<InventoryAsset> createInventoryAsset(List<Asset> assets);


}
