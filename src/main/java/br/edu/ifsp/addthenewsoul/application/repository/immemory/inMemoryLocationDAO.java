package br.edu.ifsp.addthenewsoul.application.repository.immemory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class inMemoryLocationDAO implements LocationDAO {

    private static final Map<Integer, Location> dbMemory = new LinkedHashMap<>();

    @Override
    public Optional<Location> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Location> findByLocation(Integer number, String section) {
        return Optional.empty();
    }

    @Override
    public boolean haveAssets(List<Asset> assets, Location location) {
        for (Asset asset : assets) {
            if (asset.getLocation().getId().equals(location.getId()))
                return true;
        }
        return false;
    }

    @Override
    public Integer add(Location location) {
        dbMemory.put(location.getId(), location);
        return location.getId();
    }

    @Override
    public boolean update(Location location) {
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        if (dbMemory.containsKey(key)) {
            dbMemory.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public List<Location> findAll() {
        return null;
    }
}
