package br.edu.ifsp.addthenewsoul.application.repository.inMemory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryAssetDAO implements AssetDAO {

    private static final Map<Integer, Asset> dbMemoryAsset = new LinkedHashMap<>();
    private int currentId = 0;

    @Override
    public Optional<Asset> findById(Integer id) {
        if (dbMemoryAsset.containsKey(id))
            return Optional.of(dbMemoryAsset.get(id));
        return Optional.empty();
    }

    @Override
    public List<Asset> filterByLocation(Location location) {
        return dbMemoryAsset.values().stream()
                .filter(asset -> asset.getLocation().fullLocation().contains(location.fullLocation()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Asset> filterByEmployee(Employee employee) {
        return dbMemoryAsset.values().stream()
                .filter(asset -> asset.getEmployeeInCharge().getRegistrationNumber()
                        .contains(employee.getRegistrationNumber())).collect(Collectors.toList());
    }

    @Override
    public List<Asset> filterByLocationAndEmployee(Location location, Employee employee) {
        return dbMemoryAsset.values().stream()
                .filter(asset -> asset.getLocation().fullLocation().contains(location.fullLocation()))
                .filter(asset -> asset.getEmployeeInCharge().getRegistrationNumber()
                        .contains(employee.getRegistrationNumber())).collect(Collectors.toList());
    }

    @Override
    public Integer add(Asset asset) {
        currentId += 1;
        asset.setId(currentId);
        dbMemoryAsset.put(currentId, asset);
        return asset.getId();
    }

    @Override
    public Map<Integer, Asset> bulkAdd(List<Asset> items) {
        Map<Integer, Asset> assets = new HashMap<>();

        items.stream().forEach(item -> {
            currentId++;
            assets.put(currentId, item);
        });

        dbMemoryAsset.putAll(assets);

        return assets;
    }

    @Override
    public boolean update(Asset asset) {
        if (dbMemoryAsset.containsKey(asset.getId())) {
            dbMemoryAsset.replace(asset.getId(), asset);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        if (dbMemoryAsset.containsKey(id)) {
            dbMemoryAsset.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Asset> findAll() {
        return new ArrayList<>(dbMemoryAsset.values());
    }
}
