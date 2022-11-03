package br.edu.ifsp.addthenewsoul.domain.usecases.location;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationCSV;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;

import java.io.IOException;
import java.util.List;

public class ExportLocationCSVUseCase {
    private LocationCSV locationCSV;
    private LocationDAO locationDAO;

    public ExportLocationCSVUseCase(LocationCSV locationCSV, LocationDAO locationDAO) {
        this.locationCSV = locationCSV;
        this.locationDAO = locationDAO;
    }

    public void export(String fileName) throws IOException {
        List<Location> locations = locationDAO.findAll();
        locationCSV.write(fileName, locations);
    }
}
