package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindAssetUseCase {

    private AssetDAO assetDAO;

    public FindAssetUseCase(AssetDAO assetDAO) {
        this.assetDAO = assetDAO;
    }

    public Optional<Asset> findOne (Integer id) {
        if (Validator.nullOrEmpty(String.valueOf(id)))
            throw new EntityNotFoundException("ID cannot be null or empty");
        return assetDAO.findById(id);
    }

    public List<Asset> findAll () {
        return assetDAO.findAll();
    }
}
