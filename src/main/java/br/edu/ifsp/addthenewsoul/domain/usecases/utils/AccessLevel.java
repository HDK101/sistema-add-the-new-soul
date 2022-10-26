package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

public enum AccessLevel {
    RELEASED("Access Released"),
    DENIED("Access Denied");

    final private String description;

    AccessLevel (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
