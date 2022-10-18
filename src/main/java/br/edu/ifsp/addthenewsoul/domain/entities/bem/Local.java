package br.edu.ifsp.addthenewsoul.domain.entities.bem;

public class Local {
    private String secao;
    private int numero;

    public String fullLocation() {
        return new StringBuilder().append(secao).append("").append(numero).toString();
    }
}
