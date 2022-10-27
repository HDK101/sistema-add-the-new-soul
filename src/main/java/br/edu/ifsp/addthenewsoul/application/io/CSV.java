package br.edu.ifsp.addthenewsoul.application.io;

import java.io.FileNotFoundException;
import java.util.List;

public interface CSV<T> {
    void write(String fileName, List<CSVBean> data);

    List<T> read(String fileName) throws FileNotFoundException;
}
