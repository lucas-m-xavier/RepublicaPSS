/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.state.usuario_state;

import ufes.republica.business.state.republica_state.EstadoAberta;
import ufes.republica.model.Feedback;
import ufes.republica.model.Republica;
import ufes.republica.model.Tarefa;
import ufes.republica.model.Usuario;

/**
 *
 * @author 55289
 */
public class EstadoMorador extends UsuarioState {

    public EstadoMorador(Usuario usuario) {
        super(usuario);
    }
    /*
    @Override
    public void concluirFeedback(Feedback feedback) {
        if(feedback.getEstado() instanceof EstadoEmAberto) {
            feedback.getEstado().concluirFeedback();
        }   else {
            throw new RuntimeException("Lançamento já concluido!");
        }
    }

    @Override
    public void finalizarTarefa(Tarefa tarefa) {
        if(tarefa.getEstado() instanceof EstadoPendente) {
            tarefa.getEstado().finalizarTarefa();
        }   else {
            throw new RuntimeException("Tarefa já finalizada!");
        }
    }

    @Override
    public void criarRepublica(Republica republica) {
        this.sairDaRepublica();
        this.getUsuario().setUsuarioState(new EstadoRepresentante(this.getUsuario()));
        this.getUsuario().setRepublica(republica);
        republica.setEstado(new EstadoAberta(republica));
        republica.addMorador(this.getUsuario());
    }

    @Override
    public void sairDaRepublica() {
        this.getUsuario().getRepublica().removerMorador(this.getUsuario());
        this.getUsuario().setUsuarioState(new EstadoSemTeto(this.getUsuario()));
        this.getUsuario().setRepublica(null);
    }
    */
}
