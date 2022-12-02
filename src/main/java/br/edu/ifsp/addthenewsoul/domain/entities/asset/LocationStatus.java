package br.edu.ifsp.addthenewsoul.domain.entities.asset;

public enum LocationStatus {
    NONE("Nenhum"),
    CORRECT_LOCATION("Encontrado no local correto"),
    INCORRECT_LOCATION("Encontrado no local incorreto"),
    LOST("Perdido");

    private final String name;

    LocationStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
