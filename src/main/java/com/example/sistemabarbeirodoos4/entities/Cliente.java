package com.example.sistemabarbeirodoos4.entities;

import java.util.Objects;

public class Cliente {
    String nomeCompleto;
    String email;
    String telefone;

    public Cliente(String nomeCompleto, String email, String telefone) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return nomeCompleto.equals(cliente.nomeCompleto) && email.equals(cliente.email) && telefone.equals(cliente.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCompleto, email, telefone);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
