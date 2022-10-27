package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class AssetCSVBean {
    @CsvBindByPosition(position = 0)
    public int id;
    @CsvBindByPosition(position = 1)
    public String description;
    @CsvBindByPosition(position = 2)
    public String registrationNumber;
    @CsvBindByPosition(position = 3)
    public double value;
    @CsvBindByPosition(position = 4)
    public String damage;
    @CsvBindByPosition(position = 5)
    public Integer locationId;

    public AssetCSVBean() {}

    public AssetCSVBean(int id, String description, String registrationNumber, double value, String damage, Integer locationId) {
        this.id = id;
        this.description = description;
        this.registrationNumber = registrationNumber;
        this.value = value;
        this.damage = damage;
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return description;
    }
}
