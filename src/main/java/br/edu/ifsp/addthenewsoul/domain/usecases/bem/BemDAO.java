package br.edu.ifsp.addthenewsoul.domain.usecases.bem;

import br.edu.ifsp.addthenewsoul.domain.entities.bem.Bem;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.DAO;

import java.util.Optional;

public interface BemDAO extends DAO<Bem, Integer> {
    Optional<Bem> findByCode(Integer code);
}
