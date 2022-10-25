package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

import java.util.Optional;

public interface AssetDAO extends DAO<Asset, Integer> {
    Optional<Asset> findById(Integer id);
}