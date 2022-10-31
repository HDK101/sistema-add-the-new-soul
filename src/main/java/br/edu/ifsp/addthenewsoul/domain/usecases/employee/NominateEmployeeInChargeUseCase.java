package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.EntityNotFoundException;

import java.util.List;


public class NominateEmployeeInChargeUseCase {

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
