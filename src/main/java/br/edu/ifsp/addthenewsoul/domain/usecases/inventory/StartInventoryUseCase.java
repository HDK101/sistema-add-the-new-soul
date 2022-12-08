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
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.StartInventoryException;

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
                throw new StartInventoryException("Todos os funcionários selecionados não devem ser inventariantes");
            }
        });
        employees.forEach(employee -> {
            employee.addRole(Role.EXECUTOR);
        });
    }

    public List<InventoryAsset> createInventoryAssets(List<Asset> assets) {
        assets.forEach(asset -> {
            if (asset.getLocation() == null || asset.getEmployeeInCharge() == null) {
                throw new StartInventoryException("O bem " + asset.getDescription() + " deve ter um local e um funcionário responsável");
            }
        });

        List<InventoryAsset> inventoryAssets = assets.stream().map(InventoryAsset::createFromAsset).toList();

        inventoryAssets.forEach(inventoryAsset -> {
            inventoryAsset.setStatus(Status.NOT_VERIFIED);
            inventoryAsset.setLocationStatus(LocationStatus.NONE);
        });

        return inventoryAssets;
    }

    public void initializeInventory (String name, LocalDate initialDate, LocalDate endDate, List<Employee> employees,
                                     Employee comissionPresident, List<Asset> assets) {
        if (!Validator.checkIfDateHasPassed(initialDate, endDate)) throw new StartInventoryException("Data inicial é maior que o final");
        if (!Validator.checkIfDateHasPassed(initialDate)) throw new StartInventoryException("Data inicial é menor que a data de hoje");
        if (inventoryDAO.getStatusFromInventories()) throw new StartInventoryException("Já existe um inventário aberto");
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
        List<Role> roles = comissionPresident.getRoles().stream().toList();
        inventoryDAO.updateEmployeePresident(comissionPresident, roles);
        inventoryDAO.add(inventory);
    }
}
