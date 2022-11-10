package br.edu.ifsp.addthenewsoul.application.repository.inMemory;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;

import java.time.LocalDate;
import java.util.*;

public class InMemoryInventoryDAO implements InventoryDAO {

    private final Map<Integer, Inventory> dbMemoryInventory = new LinkedHashMap<>();

    private int currentInventoryId = 0;
    private int currentInventoryAssetId = 0;

    @Override
    public Map<Integer, Inventory> bulkAdd(List<Inventory> items) {
        return null;
    }

    @Override
    public Integer add(Inventory inventory) {
        currentInventoryId++;
        dbMemoryInventory.put(currentInventoryId, inventory);

        inventory.getAssets().stream().forEach(inventoryAsset -> {
            currentInventoryAssetId++;
            inventoryAsset.setId(currentInventoryAssetId);
        });

        return currentInventoryId;
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
    public void filterByPeriod(List<Inventory> all, LocalDate initialDate, LocalDate endDate) {

    }
}
