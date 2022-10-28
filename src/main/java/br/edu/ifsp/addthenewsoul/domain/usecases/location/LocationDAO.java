package br.edu.ifsp.addthenewsoul.domain.usecases.location;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;

import java.util.List;
import java.util.Optional;

public interface LocationDAO extends DAO<Location, Integer> {

    Optional<Location> findById(Integer id);

    Optional<Location> findByLocation(Integer number, String section);

    boolean haveAssets (List<Asset> assets, Location location);
}
