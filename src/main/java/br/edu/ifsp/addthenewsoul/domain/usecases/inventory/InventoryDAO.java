package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

import java.util.Optional;


public interface InventoryDAO extends DAO<Inventory, Integer> {
    Optional<Inventory> findInventoryById(Integer id);
}
