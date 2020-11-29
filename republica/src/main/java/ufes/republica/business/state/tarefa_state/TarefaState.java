/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.state.tarefa_state;

import ufes.republica.model.Tarefa;

/**
 *
 * @author Lucas
 */
public abstract class TarefaState {
    private Tarefa tarefa;
    
    public TarefaState(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    
    public void finalizarTarefa() {
        throw new RuntimeException("Tarefa já finalizada!");
    }

    public Tarefa getTarefa() {
        return tarefa;
    }
}
