package br.edu.ifsp.addthenewsoul.domain.entities.good;

public class Local {
    private String section;
    private int number;

    public String fullLocation() {
        return new StringBuilder().append(section).append("").append(number).toString();
    }
}
