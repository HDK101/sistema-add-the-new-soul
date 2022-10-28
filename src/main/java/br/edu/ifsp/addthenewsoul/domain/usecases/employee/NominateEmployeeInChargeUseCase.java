package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;


public class NominateEmployeeInChargeUseCase {

    public void nominateEmployeeInCharge(Employee employee, Asset asset, Location location){
        if(asset != null){
            if(employee != null) {
                asset.setEmployeeInCharge(employee);
                if(location != null) {
                    asset.setLocation(location);
                }else{
                    throw new IllegalArgumentException("Location cannot be null.");
                }
            }else{
                throw new IllegalArgumentException("Employee cannot be null.");
            }
        }else{
            throw new IllegalArgumentException("Asset cannot be null.");
        }
    }
}
