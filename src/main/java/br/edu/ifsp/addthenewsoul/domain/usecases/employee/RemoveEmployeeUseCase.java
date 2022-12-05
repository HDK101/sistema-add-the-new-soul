package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.EmployeeBelongsToOpenedInventoryException;

public class RemoveEmployeeUseCase {

    private EmployeeDAO employeeDAO;
    private InventoryDAO inventoryDAO;

    public RemoveEmployeeUseCase(EmployeeDAO employeeDAO, InventoryDAO inventoryDAO) {
        this.employeeDAO = employeeDAO;
        this.inventoryDAO = inventoryDAO;
    }

    public boolean remove(String registrationNumber) {
        if (registrationNumber == null || employeeDAO.findByRegistrationNumber(registrationNumber).isEmpty())
            throw new IllegalArgumentException("Employee not found.");

        //if(employeeDAO.findByRegistrationNumber(registrationNumber).orElseThrow().getAssetsInCharge() != null)
        //   throw new IllegalArgumentException("Cannot remove employee that is in charge of assets.");

        if(inventoryDAO.getStatusFromInventories()){
           for(Inventory inventory : inventoryDAO.findAll()){
               for(Employee employee : inventory.getComission()){
                   if(employee.getRegistrationNumber().equals(registrationNumber))
                   throw new EmployeeBelongsToOpenedInventoryException("Employee cannot be removed because it belongs to an opened inventory.");
               }
           }
        }

        return employeeDAO.delete(registrationNumber);

    }
}
