package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

public class NominateEmployeeInChargeUseCase {

    public void nominateEmployeeInCharge(Employee employee, Asset asset, Local local){
        if(asset != null){
            if(employee != null) {
                asset.setEmployeeInCharge(employee);
                if(local != null) {
                    //asset.setLocal(local);
                }else{
                    throw new IllegalArgumentException("Local cannot be null.");
                }
            }else{
                throw new IllegalArgumentException("Employee cannot be null.");
            }
        }else{
            throw new IllegalArgumentException("Asset cannot be null.");
        }
    }
}
