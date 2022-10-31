package br.edu.ifsp.addthenewsoul.domain.usecases.location;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationCSV;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;

import java.io.IOException;
import java.util.List;

public class ImportLocationCSVUseCase {
    private LocationCSV locationCSV;
    private LocationDAO locationDAO;

    public ImportLocationCSVUseCase(LocationCSV locationCSV, LocationDAO locationDAO) {
        this.locationCSV = locationCSV;
        this.locationDAO = locationDAO;
    }

    public void importLocations(String fileName) throws IOException {
        List<Location> locations = locationCSV.read(fileName);
        locationDAO.bulkAdd(locations);
    }
}
