package br.edu.ifsp.addthenewsoul.domain.entities.funcionario;

public class Funcionario {
    private String nome;
    private String matricula;
    private String senhaHash;
    private String email;
    private String telefone;
    private Role role;

    public Funcionario(String nome, String matricula, String senhaHash, String email, String telefone, Role role) {
        this.nome = nome;
        this.matricula = matricula;
        this.senhaHash = senhaHash;
        this.email = email;
        this.telefone = telefone;
        this.role = role;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean hasRole(Role role) {
        return this.role == role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
