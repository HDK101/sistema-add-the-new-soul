package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StartInventoryUseCase {

    private InventoryDAO inventoryDAO;
    private EmployeeDAO employeeDAO;
    private AssetDAO assetDAO;

    public StartInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public boolean validateData (String initialDate, String endDate) {
        if (Validator.isValid(initialDate, endDate)) {
            return Validator.checkIfDateHasPassed(initialDate, endDate);
        }
        return false;
    }

    public List<Employee> listAllEmployees () {
        return employeeDAO.findAll();
    }

    public void informTeam (List<Employee> employees) {
        for (Employee employee : employees) {
            inventoryDAO.insertRoleExecutor(employee);

        }
    }

    public void initializeInventory (Integer id, String name, String initialDate, String endDate, List<Employee> employees,
                                     Employee comissionPresident, List<Asset> assets) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate dateInitial = LocalDate.parse(initialDate, formatter);
        LocalDate dateEnd = LocalDate.parse(endDate, formatter);
        comissionPresident.setRole(Role.CHAIRMAN_OF_THE_COMISSION);
        Inventory inventory = new Inventory(id, name, comissionPresident, employees, dateInitial, dateEnd,
                assetDAO.createInventoryAsset(assets));
        inventoryDAO.initializeInventory(inventory);
        inventoryDAO.add(inventory);
    }
}
