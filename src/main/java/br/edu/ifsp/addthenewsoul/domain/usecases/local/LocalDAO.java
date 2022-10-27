package br.edu.ifsp.addthenewsoul.domain.usecases.local;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

import java.util.Optional;

public interface LocalDAO extends DAO<Local, Integer> {

    Optional<Local> findById(Integer id);
    Optional<Local> findByLocation(Integer number, String section);
}
