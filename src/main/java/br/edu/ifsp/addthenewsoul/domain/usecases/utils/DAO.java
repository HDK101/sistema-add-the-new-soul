package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

import java.util.List;
import java.util.Map;


public interface DAO<T, K> {
    Map<K, T> bulkAdd(List<T> items);

    K add (T type);

    boolean update (T type);

    boolean delete (K key);

    List<T> findAll();

}
