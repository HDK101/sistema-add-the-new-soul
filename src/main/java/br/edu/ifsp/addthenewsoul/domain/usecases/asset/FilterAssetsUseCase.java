package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;

import java.util.List;

public class FilterAssetsUseCase {

    private AssetDAO assetDAO;
    private Employee employee;

    public FilterAssetsUseCase(AssetDAO assetDAO) {
        this.assetDAO = assetDAO;
    }

    public List<Asset> filterAssetsByLocal (Location location) {
        if (employee.getRole().equals(Role.INVENTORY_MANAGER))
            return assetDAO.filterByLocation(location);
        return null;
    }

    public List<Asset> filterAssetsByEmployee (Employee employee) {
        if (employee.getRole().equals(Role.INVENTORY_MANAGER))
            return assetDAO.filterByEmployee(employee);
        return null;
    }

    public List<Asset> filterAssetsByLocationAndEmployee
            (Location location, Employee employee) {
        if (employee.getRole().equals(Role.INVENTORY_MANAGER))
            return assetDAO.filterByLocationAndEmployee(location, employee);
        return null;
    }
}
