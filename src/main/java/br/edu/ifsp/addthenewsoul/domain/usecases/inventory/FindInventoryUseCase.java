package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class FindInventoryUseCase {
    private InventoryDAO inventoryDAO;

    public FindInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public Optional<Inventory> findOne (Integer id) {
        if (Validator.nullOrEmpty(id.toString()))
            throw new EntityNotFoundException("Inventory not found. ID cannot be null or empty.");
        return inventoryDAO.findInventoryById(id);
    }

    public List<Inventory> findAll () {
        return inventoryDAO.findAll();
    }
}
