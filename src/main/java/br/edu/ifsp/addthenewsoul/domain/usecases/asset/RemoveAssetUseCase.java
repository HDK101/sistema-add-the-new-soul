package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.AssetBelongsToOpenedInventoryException;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.EntityAlreadyExistsException;

public class RemoveAssetUseCase {

    private AssetDAO assetDAO;

    public RemoveAssetUseCase(AssetDAO assetDAO) {
        this.assetDAO = assetDAO;
    }

    public void removeAsset (Asset asset) {
        if (asset == null) return;

        Integer id = asset.getId();
        if (assetDAO.findById(id).isEmpty())
            throw new EntityAlreadyExistsException("This resource ID does not exist");

        boolean assetBelongsToInventory = assetDAO.findByIdWithInventoryAsset(id).get()
                                            .getInventoryAsset() != null;
        if (assetBelongsToInventory) {
            throw new AssetBelongsToOpenedInventoryException("Não é possível remover um bem associado " +
                                                              "a um inventário em andamento");
        }

        assetDAO.delete(asset.getId());
    }
}
