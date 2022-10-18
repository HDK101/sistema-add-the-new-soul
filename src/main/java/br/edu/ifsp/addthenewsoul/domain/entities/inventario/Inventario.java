package br.edu.ifsp.addthenewsoul.domain.entities.inventario;

import br.edu.ifsp.addthenewsoul.domain.entities.funcionario.Funcionario;

import java.time.LocalDate;
import java.util.List;

public class Inventario {
    private String nome;
    private Funcionario presidenteComissao;
    private List<Funcionario> equipe;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<BemInventario> bens;

    public Inventario(String nome, Funcionario presidenteComissao, List<Funcionario> equipe, LocalDate dataInicio, LocalDate dataFim, List<BemInventario> bens) {
        this.nome = nome;
        this.presidenteComissao = presidenteComissao;
        this.equipe = equipe;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.bens = bens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Funcionario getPresidenteComissao() {
        return presidenteComissao;
    }

    public void setPresidenteComissao(Funcionario presidenteComissao) {
        this.presidenteComissao = presidenteComissao;
    }

    public List<Funcionario> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<Funcionario> equipe) {
        this.equipe = equipe;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public List<BemInventario> getBens() {
        return bens;
    }
}
