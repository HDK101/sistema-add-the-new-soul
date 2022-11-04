package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

import java.util.List;

public class EditAssetUseCase {

    private AssetDAO assetDAO;

    public EditAssetUseCase(AssetDAO assetDAO) {
        this.assetDAO = assetDAO;
    }

    public List<Asset> listAssetsToUpdate () {
        return assetDAO.findAll();
    }

    public void updateAsset (Asset asset) {
        Validator<Asset> validator = new ValidationOfAssetAttributes();
        Notification notification = validator.isValid(asset);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = asset.getId();
        if (assetDAO.findById(id).isPresent())
            throw new EntityAlreadyExistsException("This asset id is already in use");

        assetDAO.update(asset);
    }
}
