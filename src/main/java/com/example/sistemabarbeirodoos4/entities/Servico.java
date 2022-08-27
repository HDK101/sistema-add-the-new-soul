package com.example.sistemabarbeirodoos4.entities;

import java.util.Objects;

public class Servico {
    String nome;
    Double comissao;
    Double preco;

    public Servico(String nome, Double comissao, Double preco) {
        this.nome = nome;
        this.comissao = comissao;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getComissao() {
        return comissao;
    }

    public void setComissao(Double comissao) {
        this.comissao = comissao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return nome.equals(servico.nome) && comissao.equals(servico.comissao) && preco.equals(servico.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, comissao, preco);
    }

    @Override
    public String toString() {
        return "Servico{" +
                "nome='" + nome + '\'' +
                ", comissao=" + comissao +
                ", preco=" + preco +
                '}';
    }
}
