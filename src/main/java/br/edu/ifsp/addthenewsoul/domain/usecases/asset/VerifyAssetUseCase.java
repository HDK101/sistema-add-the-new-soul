package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

public class VerifyAssetUseCase {

    private AssetDAO assetDAO;

    public VerifyAssetUseCase(AssetDAO assetDAO) {
        this.assetDAO = assetDAO;
    }

    public boolean verify(Asset asset) {
        if (asset == null || assetDAO.findById(asset.getId()).isEmpty())
            throw new IllegalArgumentException("Asset not found.");

        asset.setStatus(Status.VERIFIED);
        asset.setEmployeeInCharge(null);
        return assetDAO.update(asset);
    }
}
