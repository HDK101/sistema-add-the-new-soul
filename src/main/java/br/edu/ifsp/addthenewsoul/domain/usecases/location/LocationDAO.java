package br.edu.ifsp.addthenewsoul.domain.usecases.location;

import java.util.Optional;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

public interface LocationDAO extends DAO {
    Optional<Local> findById(Integer id);
}
