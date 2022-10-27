package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.application.io.CSV;
import br.edu.ifsp.addthenewsoul.application.io.CSVBean;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

public class AssetCSV implements CSV<AssetCSVBean> {
    public void writeWithAssets(List<Asset> assets, String fileName) {
        List<AssetCSVBean> assetCSVBeans = assets.stream().map(asset -> {
           AssetCSVBean assetCSVBean = new AssetCSVBean(
                   asset.getId(),
                   asset.getDescription(),
                   asset.getEmployeeInCharge().getRegistrationNumber(),
                   asset.getValue(),
                   asset.getDamage(),
                   1
           );
            return assetCSVBean;
        }).collect(Collectors.toList());
        System.out.println(assetCSVBeans);
    }

    public void write(String fileName, List<CSVBean> data) {
        try (Writer writer = new FileWriter(fileName)) {
            StatefulBeanToCsv<CSVBean> sbc = new StatefulBeanToCsvBuilder<CSVBean>(writer)
                    .withQuotechar('\'')
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            sbc.write(data);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public List<AssetCSVBean> read(String fileName) throws FileNotFoundException {
        List<AssetCSVBean> beans = new CsvToBeanBuilder<AssetCSVBean>(new FileReader(fileName))
                .withType(AssetCSVBean.class).build().parse();
        beans.stream().forEach(csv -> System.out.println(csv.toString()));
        return beans;
    }
}
