package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;

import java.util.List;

public class FilterAssetsUseCase {

    private final AssetDAO assetDAO;

    public FilterAssetsUseCase(AssetDAO assetDAO) {
        this.assetDAO = assetDAO;
    }

    public List<Asset> filterAssetsByLocal (Location location) {
        return assetDAO.filterByLocation(location);
    }

    public List<Asset> filterAssetsByEmployee (Employee employeeInCharge) {
        return assetDAO.filterByEmployee(employeeInCharge);
    }

    public List<Asset> filterAssetsByLocationAndEmployee
            (Location location, Employee employeeInCharge) {
        return assetDAO.filterByLocationAndEmployee(location, employeeInCharge);
    }
}
