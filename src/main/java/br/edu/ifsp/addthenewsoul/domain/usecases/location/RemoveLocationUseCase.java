package br.edu.ifsp.addthenewsoul.domain.usecases.location;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

public class RemoveLocationUseCase {

    private LocationDAO locationDAO;
    private AssetDAO assetDAO;

    public RemoveLocationUseCase(LocationDAO locationDAO, AssetDAO assetDAO) {
        this.locationDAO = locationDAO;
        this.assetDAO = assetDAO;
    }

    public boolean deleteLocation (Location location) {
            //haveAssets está morto
            if (location.getAssets().size() > 0)
                throw new IllegalArgumentException("A location cannot be deleted while there are assets in it.");
            return locationDAO.delete(location.getId());
    }
}
