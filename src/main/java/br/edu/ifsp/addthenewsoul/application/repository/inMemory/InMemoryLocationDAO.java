package br.edu.ifsp.addthenewsoul.application.repository.inMemory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryLocationDAO implements LocationDAO {

    private static final Map<Integer, Location> dbMemory = new LinkedHashMap<>();
    private int currentId;

    @Override
    public Optional<Location> findById(Integer id) {
        if (dbMemory.containsKey(id))
            return Optional.of(dbMemory.get(id));

        return Optional.empty();
    }

    @Override
    public Optional<Location> findByLocation(Integer number, String section) {
        return findAll().stream()
                .filter(location -> location.getNumber() == number &&
                        location.getSection().equals(section))
                .findAny();
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
        currentId++;
        dbMemory.put(currentId, location);
        return currentId;
    }

    @Override
    public Map<Integer, Location> bulkAdd(List<Location> items) {
        Map<Integer, Location> locations = new HashMap<>();

        items.stream().forEach(item -> {
            if (!locations.containsKey(item.getId())) {
                locations.put(item.getId(), item);
            }
        });

        dbMemory.putAll(locations);

        return locations;
    }

    @Override
    public boolean update(Location location) {

        if (dbMemory.containsKey(location.getId())) {
            dbMemory.replace(location.getId(), location);
            return true;
        }

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
        return new ArrayList<>(dbMemory.values());
    }
}
