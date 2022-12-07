package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InventoryStatus;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.InventoryInvalidPresidentException;

import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

import java.time.LocalDate;
import java.util.List;

public class StartInventoryUseCase {

    private InventoryDAO inventoryDAO;

    public StartInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    private void designateEmployeeAsPresident(Employee employee) {
        if (employee.getRoles().contains(Role.CHAIRMAN_OF_THE_COMISSION)) throw new InventoryInvalidPresidentException(employee, "Employee is already a president");
        employee.addRole(Role.CHAIRMAN_OF_THE_COMISSION);
    }

    private void designateComission(List<Employee> employees) {
        employees.forEach(employee -> {
            if (employee.getRoles().contains(Role.EXECUTOR)) {
                throw new IllegalArgumentException("All employess must not have the INVENTORY_MANAGER role");
            }
        });
        employees.forEach(employee -> {
            employee.addRole(Role.EXECUTOR);
        });
    }

    public List<InventoryAsset> createInventoryAssets(List<Asset> assets) {
        List<InventoryAsset> inventoryAssets = assets.stream().map(InventoryAsset::createFromAsset).toList();
        for (InventoryAsset inventoryAsset : inventoryAssets) {
            inventoryAsset.setStatus(Status.NOT_VERIFIED);
            inventoryAsset.setLocationStatus(LocationStatus.NONE);
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
        Inventory inventory = Inventory.builder()
                .name(name)
                .initialDate(initialDate)
                .endDate(endDate)
                .comissionPresident(comissionPresident)
                .comission(employees)
                .assets(createInventoryAssets(assets))
                .inventoryStatus(InventoryStatus.OPEN)
                .build();
        inventoryDAO.add(inventory);
    }
}
