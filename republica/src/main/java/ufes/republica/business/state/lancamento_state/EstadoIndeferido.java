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
public class EstadoIndeferido extends LancamentoState {

    public EstadoIndeferido(Lancamento lancamento) {
        super(lancamento);
    }

    @Override
    public void aprovarLancamento() {
        this.getLancamento().setEstado(new EstadoAprovado(this.getLancamento()));
    }

    
}
