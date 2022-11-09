package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.InventoryInvalidPresidentException;

import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StartInventoryUseCase {

    private InventoryDAO inventoryDAO;

    public StartInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    private void designateEmployeeAsPresident(Employee employee) {
        if (employee.getRole() == Role.CHAIRMAN_OF_THE_COMISSION) throw new InventoryInvalidPresidentException(employee, "Employee is already a president");
        employee.setRole(Role.CHAIRMAN_OF_THE_COMISSION);
    }

    private List<InventoryAsset> createInventoryAssets(List<Asset> assets) {
        return assets.stream().map(InventoryAsset::createFromAsset).toList();
    }

    public void initializeInventory (String name, LocalDate initialDate, LocalDate endDate, List<Employee> employees,
                                     Employee comissionPresident, List<Asset> assets) {
        if (!Validator.checkIfDateHasPassed(initialDate, endDate)) throw new IllegalArgumentException("Initial date is higher than end date");
        designateEmployeeAsPresident(comissionPresident);
        Inventory inventory = new Inventory(name, initialDate, endDate, comissionPresident, employees, createInventoryAssets(assets));
        inventoryDAO.add(inventory);
    }
}
