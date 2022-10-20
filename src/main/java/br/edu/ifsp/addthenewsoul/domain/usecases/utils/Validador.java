package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

import java.util.Collection;

public abstract class Validador<T> {

    public abstract Notification validade (T type);

    public static boolean nullOrEmpty(String string){
        return string == null || string.isEmpty();
    }

}
