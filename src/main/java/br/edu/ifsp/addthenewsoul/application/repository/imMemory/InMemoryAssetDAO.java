package br.edu.ifsp.addthenewsoul.application.repository.imMemory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryAssetDAO implements AssetDAO {

    private static final Map<Integer, Asset> dbMemoryAsset = new LinkedHashMap<>();

    @Override
    public Optional<Asset> findById(Integer id) {
        if (dbMemoryAsset.containsKey(id))
            return Optional.of(dbMemoryAsset.get(id));
        return Optional.empty();
    }

    @Override
    public List<Asset> findByLocation(List<Asset> assets, Location location) {
        return assets.stream()
                .filter(asset -> asset.getLocation().fullLocation().contains(location.fullLocation()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Asset> findByEmployee(List<Asset> assets, Employee employee) {
        return assets.stream()
                .filter(asset -> asset.getEmployeeInCharge().getRegistrationNumber()
                        .contains(employee.getRegistrationNumber())).collect(Collectors.toList());
    }

    @Override
    public List<Asset> findByLocationAndEmployee(List<Asset> assets, Location location, Employee employee) {
        return assets.stream()
                .filter(asset -> asset.getLocation().fullLocation().contains(location.fullLocation()))
                .filter(asset -> asset.getEmployeeInCharge().getRegistrationNumber()
                        .contains(employee.getRegistrationNumber())).collect(Collectors.toList());
    }

    @Override
    public Integer add(Asset asset) {
        dbMemoryAsset.put(asset.getId(), asset);
        return asset.getId();
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
