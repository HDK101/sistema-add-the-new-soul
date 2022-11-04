package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

public class EvaluateAssetUseCase {

    private AssetDAO assetDAO;

    public EvaluateAssetUseCase(AssetDAO assetDAO) {
        this.assetDAO = assetDAO;
    }

    public void evaluateAsset(Asset asset, String damage){
        asset.setDamage(damage);
        asset.setStatus(Status.VERIFIED);
    }
}
