package br.edu.ifsp.addthenewsoul.domain.usecases.good;

import br.edu.ifsp.addthenewsoul.domain.entities.good.Good;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

import java.util.Optional;

public interface GoodDAO extends DAO<Good, Integer> {
    Optional<Good> findById(Integer id);
}
