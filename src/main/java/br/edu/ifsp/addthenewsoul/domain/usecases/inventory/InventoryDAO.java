package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface InventoryDAO extends DAO<Inventory, String> {
    Optional<Inventory> findInventoryById(String id);

    void filterByPeriod(List<Inventory> all, LocalDate initialDate, LocalDate endDate);

        boolean updateEmployeePresident (Employee employee, List<Role> roles);

    boolean getStatusFromInventories();

    boolean evaluateInventoryAsset(InventoryAsset asset);
}
