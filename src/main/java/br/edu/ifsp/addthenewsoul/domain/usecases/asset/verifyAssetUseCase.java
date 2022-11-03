package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

public class verifyAssetUseCase {

    private AssetDAO assetDAO;

    public verifyAssetUseCase(AssetDAO assetDAO) {
        this.assetDAO = assetDAO;
    }

    public boolean verify(InventoryAsset asset) {
        if (asset == null || assetDAO.findById(asset.getId()).isEmpty())
            throw new IllegalArgumentException("Asset not found.");

        asset.setStatus(Status.VERIFIED);
        asset.setEmployeeInCharge(null);
        return assetDAO.update(asset);
    }
}
