package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.EntityAlreadyExistsException;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

public class AddAssetUseCase {

    private AssetDAO assetDAO;

    public AddAssetUseCase(AssetDAO assetDAO) {
        this.assetDAO = assetDAO;
    }

    public Integer add (Asset asset) {
        Validator<Asset> validator = new ValidationOfAssetAttributes();
        Notification notification = validator.isValid(asset);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        return assetDAO.add(asset);
    }
}
