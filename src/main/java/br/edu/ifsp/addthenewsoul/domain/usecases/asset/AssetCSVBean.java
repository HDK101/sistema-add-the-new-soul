package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.application.io.CSVBean;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import com.opencsv.bean.CsvBindByName;

public class AssetCSVBean extends CSVBean {
    @CsvBindByName()
    private int id;
    @CsvBindByName()
    private String description;
    @CsvBindByName()
    private String registrationNumber;
    @CsvBindByName()
    private double value;
    @CsvBindByName()
    private String damage;
    @CsvBindByName()
    private Integer locationId;

    public AssetCSVBean(int id, String description, String registrationNumber, double value, String damage, Integer locationId) {
        this.id = id;
        this.description = description;
        this.registrationNumber = registrationNumber;
        this.value = value;
        this.damage = damage;
        this.locationId = locationId;
    }
}
