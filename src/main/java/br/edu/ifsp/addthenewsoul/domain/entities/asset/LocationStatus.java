package br.edu.ifsp.addthenewsoul.domain.entities.asset;

public enum LocationStatus {
    CORRECT_LOCATION("Encontrado no local correto"),
    INCORRECT_LOCATION("Encontrado no local incorreto"),
    LOST("Perdido");

    private final String name;

    LocationStatus(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
