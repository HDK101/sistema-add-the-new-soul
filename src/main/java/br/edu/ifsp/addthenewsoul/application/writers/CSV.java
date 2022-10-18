package br.edu.ifsp.addthenewsoul.application.writers;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

public class CSV {
    public static <CsvBean> void write(List<CsvBean> data) {
        try (Writer writer = new FileWriter("file.csv")) {
            StatefulBeanToCsv<CsvBean> sbc = new StatefulBeanToCsvBuilder<CsvBean>(writer)
                    .withQuotechar('\'')
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            sbc.write(data);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
