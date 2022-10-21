package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

import java.util.List;

public interface DAO<T, K> {
    K add (T type);

    List<T> findAll();

    boolean update (T type);

    boolean delete (T type);

}
