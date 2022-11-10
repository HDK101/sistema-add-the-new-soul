package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;


public class NominateEmployeeInChargeUseCase {

    private EmployeeDAO employeeDAO;

    public NominateEmployeeInChargeUseCase(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void nominateEmployeeInCharge(Employee employee, Asset asset, Location location){
        if (employee == null) throw new IllegalArgumentException("Location cannot be null.");
        if (asset == null) throw new IllegalArgumentException("Employee cannot be null.");
        if (location == null) throw new IllegalArgumentException("Location cannot be null.");
        
        employee.getAssetsInCharge().add(asset);
        asset.setEmployeeInCharge(employee);
        asset.setLocation(location);
        location.getAssets().add(asset);
    }
}
