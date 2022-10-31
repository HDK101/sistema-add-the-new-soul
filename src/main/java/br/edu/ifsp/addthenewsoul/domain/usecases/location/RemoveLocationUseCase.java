package br.edu.ifsp.addthenewsoul.domain.usecases.location;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

public class RemoveLocationUseCase {

    private Employee employee;
    private LocationDAO locationDAO;
    private AssetDAO assetDAO;

    public RemoveLocationUseCase(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public boolean deleteLocation (Location location) {
        if (employee.getRole().equals(Role.INVENTORY_MANAGER)) {
            if (locationDAO.haveAssets(assetDAO.findAll(), location))
                throw new IllegalArgumentException("A location cannot be deleted while there are assets in it.");
            return locationDAO.delete(location.getId());
        }
        return false;
    }
}
