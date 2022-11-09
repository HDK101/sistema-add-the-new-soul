package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.EntityNotFoundException;


public class NominateEmployeeInChargeUseCase {

    private EmployeeDAO employeeDAO;

    public NominateEmployeeInChargeUseCase(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void nominateEmployeeInCharge(Employee employee, Asset asset, Location location){
        if(asset != null){
            employee.getAssetsInCharge().add(asset);
            if(employee != null) {
                asset.setEmployeeInCharge(employee);
                if(location != null) {
                    asset.setLocation(location);
                }else{
                    throw new EntityNotFoundException("Location cannot be null.");
                }
            }else{
                throw new EntityNotFoundException("Employee cannot be null.");
            }
        }else{
            throw new EntityNotFoundException("Asset cannot be null.");
        }
    }
}
