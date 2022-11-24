package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;

import java.util.List;

public class FilterAssetsUseCase {

    private AssetDAO assetDAO;
    private Employee employee;

    public FilterAssetsUseCase(AssetDAO assetDAO, Employee employee) {
        this.assetDAO = assetDAO;
        this.employee = employee;
    }

    public List<Asset> filterAssetsByLocal (Location location) {
        if (employee.getRoles().equals(Role.INVENTORY_MANAGER))
            return assetDAO.filterByLocation(location);
        return null;
    }

    public List<Asset> filterAssetsByEmployee (Employee employeeInCharge) {
        if (employee.getRoles().equals(Role.INVENTORY_MANAGER))
            return assetDAO.filterByEmployee(employeeInCharge);
        return null;
    }

    public List<Asset> filterAssetsByLocationAndEmployee
            (Location location, Employee employeeInCharge) {
        if (employee.getRoles().equals(Role.INVENTORY_MANAGER))
            return assetDAO.filterByLocationAndEmployee(location, employeeInCharge);
        return null;
    }
}
