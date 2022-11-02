package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetCSV;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.EmployeeDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportAssetCSVUseCase {
    private AssetCSV assetCSV;
    private AssetDAO assetDAO;
    private LocationDAO locationDAO;
    private EmployeeDAO employeeDAO;

    public ImportAssetCSVUseCase(AssetCSV assetCSV, AssetDAO assetDAO, LocationDAO locationDAO, EmployeeDAO employeeDAO) {
        this.assetCSV = assetCSV;
        this.assetDAO = assetDAO;
        this.locationDAO = locationDAO;
        this.employeeDAO = employeeDAO;
    }

    public ImportAssetCSVUseCase(AssetCSV assetCSV, AssetDAO assetDAO) {
        this.assetCSV = assetCSV;
        this.assetDAO = assetDAO;
    }

    private Map<String, Employee> employeesToMap(List<Employee> employees) {
        Map<String, Employee> employeeMap = new HashMap<>();
        employees.stream().forEach(employee -> {
            employeeMap.put(employee.getRegistrationNumber(), employee);
        });
        return employeeMap;
    }

    private Map<Integer, Location> locationsToMap(List<Location> locations) {
        Map<Integer, Location> locationMap = new HashMap<>();
        locations.stream().forEach(location -> {
            locationMap.put(location.getId(), location);
        });
        return locationMap;
    }

    public void importAssets(String fileName, boolean withInvalidDependencies) throws IOException {
        List<Employee> employees = employeeDAO.findAll();
        List<Location> locations = locationDAO.findAll();
        //List<Asset> assets = assetCSV.read(fileName, withInvalidDependencies, employeesToMap(employees), locationsToMap(locations));
        //assetDAO.bulkAdd(assets);
    }

    public void importAssets(String fileName) throws IOException {
        List<Asset> assets = assetCSV.read(fileName);
        assetDAO.bulkAdd(assets);
    }
}
