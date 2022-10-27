package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.application.io.CSV;
import br.edu.ifsp.addthenewsoul.application.io.CSVNode;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AssetCSV implements CSV<Asset> {
    public void write(String fileName, List<Asset> data) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

        for(CSVNode d : data) {
            bufferedWriter.append(d.toCSV());
            bufferedWriter.append('\n');
        }

        bufferedWriter.close();
    }

    public List<Asset> read(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner reader = new Scanner(file);
        List<Asset> assets = new ArrayList<>();

        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(",");
            assets.add(new Asset(
                Integer.parseInt(parts[0]),
                parts[1],
                Double.parseDouble(parts[3]),
                parts[4]
            ));
        }

        reader.close();

        return assets;
    }
}
