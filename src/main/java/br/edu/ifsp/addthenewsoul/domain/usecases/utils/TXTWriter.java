package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TXTWriter {
    private StringBuilder reportBuilder;

    protected void addDetail(String head, String content) {
        reportBuilder
                .append(head).append(content).append("\n");
    }

    protected void addDetail(String head, Object content) {
        reportBuilder
                .append(head).append(content).append("\n");
    }

    protected void addSingleHead(String head) {
        reportBuilder
                .append(head).append("\n");
    }

    protected void addAssetDetail(String head, String content) {
        reportBuilder.append(" ")
                .append(head).append(content).append("\n");
    }

    protected void addAssetDetail(String head, Object content) {
        reportBuilder.append(" ")
                .append(head).append(content).append("\n");
    }

    protected void writeTxtFile() throws IOException {
        File file = new File("report.txt");
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.append(reportBuilder);
            System.out.println("File created.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not created.");
        } finally {
            if (writer != null) writer.close();
        }
    }
}
