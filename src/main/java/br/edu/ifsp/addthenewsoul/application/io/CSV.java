package br.edu.ifsp.addthenewsoul.application.io;

import java.io.FileNotFoundException;
import java.util.List;
import java.io.IOException;

public interface CSV<T extends CSVNode> {
    void write(String fileName, List<T> data) throws IOException;

    List<T> read(String fileName) throws FileNotFoundException;
}
