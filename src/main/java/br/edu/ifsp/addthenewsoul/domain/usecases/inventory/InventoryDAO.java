package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface InventoryDAO extends DAO<Inventory, Integer> {
    Optional<Inventory> findInventoryById(Integer id);

    void filterByPeriod(List<Inventory> all, LocalDate initialDate, LocalDate endDate);
}
