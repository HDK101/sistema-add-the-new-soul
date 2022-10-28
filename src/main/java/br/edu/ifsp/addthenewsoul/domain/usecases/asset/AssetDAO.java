package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface AssetDAO extends DAO<Asset, Integer> {
    Optional<Asset> findById(Integer id);

    List<Asset> findByLocation (List<Asset> assets, Location location);

    List<Asset> findByEmployee (List<Asset> assets, Employee employee);

    List<Asset> findByLocationAndEmployee (List<Asset> assets, Location location, Employee employee);
}
