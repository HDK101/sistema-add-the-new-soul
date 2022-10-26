package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

public class Singleton {

    private static Singleton SINGLETON;
    private AccessLevel accessLevel;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (SINGLETON == null)
            SINGLETON = new Singleton();
        return SINGLETON;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}
