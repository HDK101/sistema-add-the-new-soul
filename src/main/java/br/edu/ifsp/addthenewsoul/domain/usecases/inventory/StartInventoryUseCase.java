package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InventoryInvalidPresidentException;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StartInventoryUseCase {

    private InventoryDAO inventoryDAO;

    public StartInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    private void designateEmployeeAsPresident(Employee employee) {
        if (employee.getRole() == Role.CHAIRMAN_OF_THE_COMISSION) throw new InventoryInvalidPresidentException(employee, "Employee is already a president");
        employee.setRole(Role.CHAIRMAN_OF_THE_COMISSION);
    }

    public void initializeInventory (String name, LocalDate initialDate, LocalDate endDate, List<Employee> employees,
                                     Employee comissionPresident, List<Asset> assets) {
        if (!Validator.checkIfDateHasPassed(initialDate, endDate)) throw new IllegalArgumentException("Initial date is higher than end date");
        designateEmployeeAsPresident(comissionPresident);
        Inventory inventory = new Inventory(name, initialDate, endDate, comissionPresident, employees, assets);
        inventoryDAO.add(inventory);
    }
}
