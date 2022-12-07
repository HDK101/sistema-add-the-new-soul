package br.edu.ifsp.addthenewsoul.domain.entities.inventory;

public enum Status {
    VERIFIED("Verificado"),
    NOT_VERIFIED("Não verificado");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
