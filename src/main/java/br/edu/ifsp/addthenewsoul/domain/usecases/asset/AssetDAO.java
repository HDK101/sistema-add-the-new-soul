package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface AssetDAO extends DAO<Asset, Integer> {
    Optional<Asset> findById(Integer id);

    List<Asset> filterByLocation (List<Asset> assets, Location location);

    List<Asset> filterByEmployee (List<Asset> assets, Employee employee);

    List<Asset> filterByLocationAndEmployee (List<Asset> assets, Location location, Employee employee);


}
