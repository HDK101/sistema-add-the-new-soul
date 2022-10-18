package br.edu.ifsp.addthenewsoul.domain.entities.bem;

import br.edu.ifsp.addthenewsoul.domain.entities.funcionario.Funcionario;

public class Bem {
    private int codigo;
    private String descricao;
    private Funcionario responsavel;
    private double valor;
    private String avaria;

    public Bem() {}

    public Bem(int codigo, String descricao, Funcionario responsavel, double valor, String avaria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.valor = valor;
        this.avaria = avaria;
    }



    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getAvaria() {
        return avaria;
    }

    public void setAvaria(String avaria) {
        this.avaria = avaria;
    }
}
