package br.edu.ifsp.addthenewsoul.domain.entities.inventario;

import br.edu.ifsp.addthenewsoul.domain.entities.bem.Bem;
import br.edu.ifsp.addthenewsoul.domain.entities.funcionario.Funcionario;

public class BemInventario extends Bem {
    private Funcionario inventariante;

    public BemInventario(int codigo, String descricao, Funcionario responsavel, double valor, String avaria, Funcionario inventariante) {
        super(codigo, descricao, responsavel, valor, avaria);
        this.inventariante = inventariante;
    }

    public Funcionario getInventariante() {
        return inventariante;
    }
}
