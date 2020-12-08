/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.state.tarefa_state;

import ufes.republica.model.Tarefa;

import java.time.LocalDate;

/**
 *
 * @author Lucas
 */
public class EstadoPendente extends TarefaState {

    public EstadoPendente(Tarefa tarefa) {
        super(tarefa);
    }
    
    @Override
    public void finalizarTarefa() {
        this.getTarefa().setDataTermino(LocalDate.now());
        this.getTarefa().setEstado(new EstadoFinalizada(this.getTarefa()));
    }
}
