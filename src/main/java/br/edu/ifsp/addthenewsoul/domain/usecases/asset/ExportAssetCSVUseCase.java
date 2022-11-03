package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetCSV;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

import java.io.IOException;
import java.util.List;

public class ExportAssetCSVUseCase {
    private AssetCSV assetCSV;
    private AssetDAO assetDAO;

    public ExportAssetCSVUseCase(AssetCSV assetCSV, AssetDAO assetDAO) {
        this.assetCSV = assetCSV;
        this.assetDAO = assetDAO;
    }

    public void export(String fileName) throws IOException {
        List<Asset> assets = assetDAO.findAll();
        assetCSV.write(fileName, assets);
    }
}
