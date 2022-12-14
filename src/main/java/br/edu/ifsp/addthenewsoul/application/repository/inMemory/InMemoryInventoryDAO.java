/*
package br.edu.ifsp.addthenewsoul.application.repository.inMemory;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InventoryStatus;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryInventoryDAO implements InventoryDAO {

    private final Map<Integer, Inventory> dbMemoryInventory = new LinkedHashMap<>();

    private int currentInventoryId = 0;
    private int currentInventoryAssetId = 0;

    @Override
    public Map<String, Inventory> bulkAdd(List<Inventory> items) {
        return null;
    }

    @Override
    public String add(Inventory inventory) {
        currentInventoryId++;
        dbMemoryInventory.put(currentInventoryId, inventory);

        inventory.getAssets().forEach(inventoryAsset -> {
            currentInventoryAssetId++;
            inventoryAsset.setId(currentInventoryAssetId);
        });

        inventory.setInventoryStatus(InventoryStatus.OPENED);

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
    public Optional<Inventory> findInventoryById(String id) {
        if (dbMemoryInventory.containsKey(id))
            return Optional.of(dbMemoryInventory.get(id));
        return Optional.empty();
    }

    @Override
    public void filterByPeriod(List<Inventory> all, LocalDate initialDate, LocalDate endDate) {

    }

    @Override
    public boolean getStatusFromInventories() {
        List<Inventory> inventories = findAll();
        for (Inventory inventory : inventories) {
            if (inventory.getInventoryStatus().equals(InventoryStatus.OPENED))
                return true;
        }
        return false;
    }
}
*/

package br.edu.ifsp.addthenewsoul.application.repository.inMemory;

import br.edu.ifsp.addthenewsoul.application.repository.database.Database;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class InMemoryInventoryDAO implements InventoryDAO {

    @Override
    public Optional<Inventory> findInventoryById(String id) {
        return Optional.empty();
    }

    @Override
    public void clearInventoryRoles() {

    }

    @Override
    public void filterByPeriod(List<Inventory> all, LocalDate initialDate, LocalDate endDate) {

    }

    @Override
    public boolean updateEmployeePresident(Employee employee, List<Role> roles) {
        return false;
    }

    @Override
    public boolean getStatusFromInventories() {
        return false;
    }

    @Override
    public boolean evaluateInventoryAsset(InventoryAsset asset) {
        return false;
    }

    @Override
    public Map<String, Inventory> bulkAdd(List<Inventory> items) {
        return null;
    }

    @Override
    public String add(Inventory type) {
        return null;
    }

    @Override
    public boolean update(Inventory type) {
        return false;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public List<Inventory> findAll() {
        return null;
    }
}