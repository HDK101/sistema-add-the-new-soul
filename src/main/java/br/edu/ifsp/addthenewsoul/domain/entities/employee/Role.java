package br.edu.ifsp.addthenewsoul.domain.entities.employee;

import java.util.Arrays;

public enum Role {
    INVENTORY_MANAGER("Inventory Manager"),
    EXECUTOR("Executor");

    private String description;

    Role(String description) {
        this.description = description;
    }

    public static Role toEnum(String value) {
        return Arrays.stream(Role.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getDescription() {
        return description;
    }
}
