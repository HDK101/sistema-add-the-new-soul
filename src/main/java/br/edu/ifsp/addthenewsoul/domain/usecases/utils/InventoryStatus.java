package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

public enum InventoryStatus {
    OPENED("Open Inventory"),
    CLOSED("Closed Inventory");

    final private String description;

    InventoryStatus (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}