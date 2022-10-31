package br.edu.ifsp.addthenewsoul.application.repository.imMemory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryAssetDAO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InMemoryInventoryAssetDAO implements InventoryAssetDAO {

    private static final Map<Integer, Asset> dbMemoryInventoryAsset = new LinkedHashMap<>();

    @Override
    public Integer add(InventoryAsset inventoryAsset) { return null;}

    @Override
    public boolean update(InventoryAsset type) {
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public List<InventoryAsset> findAll() {
        return null;
    }
}
