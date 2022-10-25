package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

import java.util.List;
import java.util.Optional;

public interface DAO<T, K> {
    K add (T type);

    boolean update (T type);

    boolean delete (K key);

    List<T> findAll();

}
