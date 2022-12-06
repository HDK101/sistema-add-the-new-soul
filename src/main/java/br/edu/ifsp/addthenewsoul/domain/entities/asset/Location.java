package br.edu.ifsp.addthenewsoul.domain.entities.asset;

import br.edu.ifsp.addthenewsoul.application.io.CSVNode;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class Location implements CSVNode {
    private Integer id;
    private Integer number;
    private String section;
    private List<Asset> assets;

    public void setId(Integer id) {
        this.id = id;
    }

    public String fullLocation() {
        return new StringBuilder().append(section).append(" ").append(number).toString();
    }

    public Integer getId() {
        return id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void addAsset(Asset asset) {
        this.assets.add(asset);
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Location{");
        sb.append("id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", section='").append(section).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String toCSV() {
        StringBuilder builder = new StringBuilder();
        builder
                .append(id).append(",")
                .append(number).append(",")
                .append(section);
        return builder.toString();
    }
}
