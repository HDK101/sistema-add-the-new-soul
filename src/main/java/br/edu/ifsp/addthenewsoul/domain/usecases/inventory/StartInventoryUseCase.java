package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StartInventoryUseCase {

    private InventoryDAO inventoryDAO;

    public StartInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public void initializeInventory (LocalDate initialDate, LocalDate endDate, List<Employee> employees,
                                     Employee comissionPresident) {
        for (Employee employee : employees) {
            if (employee.getRole().equals(Role.INVENTORY_MANAGER))
                throw new IllegalArgumentException("Employee can not be inventory manager type");
        }

        if (comissionPresident.getRole().equals(Role.INVENTORY_MANAGER))
            throw new IllegalArgumentException("Employee can not be inventory manager type");

    }
}
