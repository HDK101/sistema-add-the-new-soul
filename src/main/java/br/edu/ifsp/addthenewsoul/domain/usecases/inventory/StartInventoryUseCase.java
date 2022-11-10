package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
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

    private void designateComission(List<Employee> employees) {
        employees.stream().forEach(employee -> {
            if (employee.getRole() != Role.EMPLOYEE) {
                throw new IllegalArgumentException("All employess must have Role.EMPLOYEE role");
            }
        });
        employees.stream().forEach(employee -> {
            employee.setRole(Role.EXECUTOR);
        });
    }

    private List<InventoryAsset> createInventoryAssets(List<Asset> assets) {
        List<InventoryAsset> inventoryAssets = assets.stream().map(InventoryAsset::createFromAsset).toList();
        for (InventoryAsset inventoryAsset : inventoryAssets) {
            inventoryAsset.setStatus(Status.NOT_VERIFIED);
        }
        return inventoryAssets;
    }

    public void initializeInventory (String name, LocalDate initialDate, LocalDate endDate, List<Employee> employees,
                                     Employee comissionPresident, List<Asset> assets) {
        if (!Validator.checkIfDateHasPassed(initialDate, endDate)) throw new IllegalArgumentException("Initial date is higher than end date");
        if (!Validator.checkIfDateHasPassed(initialDate)) throw new IllegalArgumentException("Initial date is less than the current day");
        if (inventoryDAO.getStatusFromInventories()) throw new IllegalArgumentException("There is currently an open inventory");
        designateEmployeeAsPresident(comissionPresident);
        designateComission(employees);
        Inventory inventory = new Inventory(name, initialDate, endDate, comissionPresident, employees, createInventoryAssets(assets));
        inventoryDAO.add(inventory);
    }
}
