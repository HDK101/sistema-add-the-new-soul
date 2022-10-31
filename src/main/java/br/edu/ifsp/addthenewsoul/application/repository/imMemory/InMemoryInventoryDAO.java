package br.edu.ifsp.addthenewsoul.application.repository.imMemory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;

import java.util.*;

public class InMemoryInventoryDAO implements InventoryDAO {

    private final Map<Integer, Inventory> dbMemoryInventory = new LinkedHashMap<>();

    @Override
    public Integer add(Inventory inventory) {
        dbMemoryInventory.put(inventory.getId(), inventory);
        return inventory.getId();
    }

    @Override
    public boolean update(Inventory inventory) {
        if (dbMemoryInventory.containsKey(inventory.getId())) {
            dbMemoryInventory.replace(inventory.getId(), inventory);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        if (dbMemoryInventory.containsKey(id)) {
            dbMemoryInventory.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Inventory> findAll() {
        return new ArrayList<>(dbMemoryInventory.values());
    }

    @Override
    public Optional<Inventory> findInventoryById(Integer id) {
        if (dbMemoryInventory.containsKey(id))
            return Optional.of(dbMemoryInventory.get(id));
        return Optional.empty();
    }

    @Override
    public void insertRoleExecutor(Employee employee) {
        employee.setRole(Role.EXECUTOR);
    }

    @Override
    public void initializeInventory(Inventory inventory) {
        inventory.setassets(inventory.getassets());
        System.out.println("Created Inventory");
    }

    @Override
    public boolean haveUnverifiedInventory(List<InventoryAsset> inventoryAssets) {
        for (InventoryAsset inventoryAsset : inventoryAssets) {
            if (inventoryAsset.getStatus().equals(Status.VERIFIED))
                return false;
        }
        return true;
    }

    @Override
    public void removeRoleExecutor(List<Employee> employees) {
        for (Employee employee : employees) {
            employee.setRole(Role.EMPLOYEE);
        }
    }

    @Override
    public void finalizeInventory(Inventory inventory) {
        inventory.setassets(null);
    }


    @Override
    public List<InventoryAsset> createInventoryAsset(List<Asset> assets) {
        List<InventoryAsset> inventoryAssets = new ArrayList<>();
        for (Asset asset : assets) {
            inventoryAssets.add(new InventoryAsset(asset.getId(), asset.getDescription(),
                    asset.getEmployeeInCharge(), asset.getValue(), asset.getDamage(), asset.getLocation(),
                    Status.NOT_VERIFIED));
        }
        return inventoryAssets;
    }
}
