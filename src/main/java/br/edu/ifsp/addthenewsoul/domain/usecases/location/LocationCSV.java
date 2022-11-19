package br.edu.ifsp.addthenewsoul.domain.usecases.location;

import br.edu.ifsp.addthenewsoul.application.io.CSV;
import br.edu.ifsp.addthenewsoul.application.io.CSVNode;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LocationCSV extends CSV<Location> {
    @Override
    public List<Location> read(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner reader = new Scanner(file);
        List<Location> locations = new ArrayList<>();

        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(",");

            locations.add(Location.builder()
                    .id(Integer.parseInt(parts[0]))
                    .number(Integer.parseInt(parts[1]))
                    .section(parts[2])
                    .build());
        }

        reader.close();

        return locations;
    }
}
