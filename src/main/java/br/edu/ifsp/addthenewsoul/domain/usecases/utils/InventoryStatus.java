package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

public enum InventoryStatus {
    OPEN("Aberto"),
    CLOSED("Concluído");

    final private String description;

    InventoryStatus (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
