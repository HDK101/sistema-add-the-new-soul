package br.edu.ifsp.addthenewsoul.domain.usecases.location;

import br.edu.ifsp.addthenewsoul.application.io.CSV;
import br.edu.ifsp.addthenewsoul.application.io.CSVNode;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LocationCSV implements CSV<Location> {
    @Override
    public void write(String fileName, List<Location> data) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

        for(CSVNode d : data) {
            bufferedWriter.append(d.toCSV());
            bufferedWriter.append('\n');
        }

        bufferedWriter.close();
    }

    @Override
    public List<Location> read(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner reader = new Scanner(file);
        List<Location> locations = new ArrayList<>();

        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(",");
            locations.add(new Location(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    parts[2]
            ));
        }

        reader.close();

        return locations;
    }
}
