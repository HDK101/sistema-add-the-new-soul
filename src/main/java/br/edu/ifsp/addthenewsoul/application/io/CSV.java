package br.edu.ifsp.addthenewsoul.application.io;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.io.IOException;

public abstract class CSV<T extends CSVNode> {
    public final void write(String fileName, List<T> data) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

        for(CSVNode d : data) {
            bufferedWriter.append(d.toCSV());
            bufferedWriter.append('\n');
        }

        bufferedWriter.close();
    }

    public abstract List<T> read(String fileName) throws FileNotFoundException;
}
