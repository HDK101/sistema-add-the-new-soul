package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.application.io.CSV;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AssetCSV extends CSV<Asset> {
    @Override
    public List<Asset> read(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner reader = new Scanner(file);
        List<Asset> assets = new ArrayList<>();

        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(",");
            assets.add(Asset.builder()
                    .id(Integer.parseInt(parts[0]))
                    .description(parts[1])
                    .value(Double.parseDouble(parts[2]))
                    .damage(parts[3])
                    .status(Status.NOT_VERIFIED)
                    .locationStatus(LocationStatus.NONE)
                    .build());
        }

        reader.close();

        return assets;
    }

    /*
    public List<Asset> readWithDependencies(String fileName, boolean withInvalidDependencies, Map<String, Employee> employees, Map<Integer, Location> locations) throws AssetDependencyNotFoundException, FileNotFoundException {
        List<Asset> assets = read(fileName);

        for (Asset asset : assets) {
            Employee employee = employees.get(asset.getRegistrationNumber());
            Location location = locations.get(asset.getLocalId());

            if (location == null || employee == null) {
                if (withInvalidDependencies) throw new AssetDependencyNotFoundException("Local and/or employee not found");
            }
            else {
                asset.setLocation(location);
                asset.setEmployeeInCharge(employee);
            }
        }

        return assets;
    }
     */
}
