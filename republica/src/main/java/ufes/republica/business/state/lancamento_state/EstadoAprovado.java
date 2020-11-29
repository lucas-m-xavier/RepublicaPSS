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
public class EstadoAprovado extends LancamentoState{
    public EstadoAprovado(Lancamento lancamento) {
        super(lancamento);
    }
}
