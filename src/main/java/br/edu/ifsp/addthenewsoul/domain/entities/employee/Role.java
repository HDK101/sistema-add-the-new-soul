package br.edu.ifsp.addthenewsoul.domain.entities.employee;

import java.util.Arrays;

public enum Role {
    CHAIRMAN_OF_THE_COMISSION("Presidente da comissão"),
    EXECUTOR("Inventariante"),
    INVENTORY_MANAGER("Almoxarife");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public static Role toEnum(String value){
        return Arrays.stream(Role.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getName() { return name; }

}
