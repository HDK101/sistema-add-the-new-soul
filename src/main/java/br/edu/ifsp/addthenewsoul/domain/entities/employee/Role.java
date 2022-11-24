package br.edu.ifsp.addthenewsoul.domain.entities.employee;

import java.util.Arrays;
import java.util.EnumSet;

public enum Role {
    EMPLOYEE,
    CHAIRMAN_OF_THE_COMISSION,
    INVENTORY_MANAGER,
    EXECUTOR;


    public static Role toEnum(String value){
        return Arrays.stream(Role.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
