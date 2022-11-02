package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TXTWriter {
    protected void writeTxtFile(StringBuilder stringBuilder) throws IOException {
        File file = new File("report.txt");
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.append(stringBuilder);
            System.out.println("File created.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not created.");
        } finally {
            if (writer != null) writer.close();
        }
    }
}
