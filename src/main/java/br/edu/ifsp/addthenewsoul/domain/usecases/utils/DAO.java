package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

import java.util.List;

public interface DAO<T, K> {
    K create (T type);

    List<T> findAll();

    boolean upadte (T type);

    boolean delete (T type);

}
