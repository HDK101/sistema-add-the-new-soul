package br.edu.ifsp.addthenewsoul.application.repository.immemory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryAssetDAO implements AssetDAO {

    private static final Map<Integer, Asset> dbMemory = new LinkedHashMap<>();

    @Override
    public Optional<Asset> findById(Integer id) {
        if (dbMemory.containsKey(id))
            return Optional.of(dbMemory.get(id));
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
        dbMemory.put(asset.getId(), asset);
        return asset.getId();
    }

    @Override
    public boolean update(Asset asset) {
        if (dbMemory.containsKey(asset.getId())) {
            dbMemory.replace(asset.getId(), asset);
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
    public List<Asset> findAll() {
        return new ArrayList<>(dbMemory.values());
    }
}
