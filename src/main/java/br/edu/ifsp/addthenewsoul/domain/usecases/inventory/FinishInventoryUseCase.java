package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InventoryStatus;

import java.util.EnumSet;

public class FinishInventoryUseCase {

    private final InventoryDAO inventoryDAO;

    public FinishInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public void finalizeInventory (Inventory inventory) {
        if (inventory == null) return;

        if (!inventory.getInventoryStatus().equals(InventoryStatus.OPEN))
            throw new IllegalStateException("Este inventário não está aberto");
        if (inventory.hasUnverifiedAssets())
            throw new IllegalStateException("O inventário só pode ser marcado como finalizado quando todos os bens tiverem sido verificados");

        inventory.finish();
        inventoryDAO.clearInventoryRoles();
        inventoryDAO.update(inventory);
    }
}
