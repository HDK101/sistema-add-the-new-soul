package com.example.sistemabarbeirodoos4.entities;

import java.util.Objects;

public class Funcionario {
    String nomeCompleto;
    String email;
    String telefone;
    String endereco;
    String usuario;
    String senha;

    public Funcionario(String nomeCompleto, String email, String telefone, String endereco, String usuario, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNome(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return nomeCompleto.equals(that.nomeCompleto) && email.equals(that.email) && telefone.equals(that.telefone) && endereco.equals(that.endereco) && usuario.equals(that.usuario) && senha.equals(that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCompleto, email, telefone, endereco, usuario, senha);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nomeCompleto + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
