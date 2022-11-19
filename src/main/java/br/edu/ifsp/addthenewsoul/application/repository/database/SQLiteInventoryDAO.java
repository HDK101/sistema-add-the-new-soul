package br.edu.ifsp.addthenewsoul.application.repository.database;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SQLiteInventoryDAO implements InventoryDAO {
    @Override
    public Optional<Inventory> findInventoryById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void filterByPeriod(List<Inventory> all, LocalDate initialDate, LocalDate endDate) {

    }

    @Override
    public boolean getStatusFromInventories() {
        return false;
    }

    @Override
    public Map<Integer, Inventory> bulkAdd(List<Inventory> items) {
        return null;
    }

    @Override
    public Integer add(Inventory type) {
        return null;
    }

    @Override
    public boolean update(Inventory type) {
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public List<Inventory> findAll() {
        return null;
    }
}
