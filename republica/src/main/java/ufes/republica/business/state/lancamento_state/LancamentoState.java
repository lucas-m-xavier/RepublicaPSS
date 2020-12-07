/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.state.lancamento_state;

import ufes.republica.model.Lancamento;

/**
 *
 * @author Lucas
 */
public abstract class LancamentoState{
    private Lancamento lancamento;
    
    public LancamentoState(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public void aprovarLancamento() {
        throw new RuntimeException("Um lançamento só pode ser aprovado se ele estiver indeferido!");
    }

    public Lancamento getLancamento() {
        return lancamento;
    }
}
