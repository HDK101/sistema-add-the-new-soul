package br.edu.ifsp.addthenewsoul.domain.entities.employee;

import java.util.Arrays;
import java.util.EnumSet;

public enum Role {
    CHAIRMAN_OF_THE_COMISSION("Presidente da comissão"),
    INVENTORY_MANAGER("Inventariante"),
    EXECUTOR("Almoxarife");

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

    public String toString() {
        return name;
    }


}
