/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.state.usuario_state;

import ufes.republica.business.state.feedback_state.EstadoEmAberto;
import ufes.republica.business.state.lancamento_state.EstadoIndeferido;
import ufes.republica.business.state.republica_state.EstadoAberta;
import ufes.republica.business.state.tarefa_state.EstadoPendente;
import ufes.republica.model.*;

/**
 *
 * @author 55289
 */
public class EstadoRepresentante extends UsuarioState {

    public EstadoRepresentante(Usuario usuario) {
        super(usuario);
    }

    @Override
    public void aprovarLancamento(Lancamento lancamento) {
        if (lancamento.getEstado() instanceof EstadoIndeferido) {
            lancamento.getEstado().aprovarLancamento();
        }   else {
            throw new RuntimeException("Um lançamento já aprovado, não pode ser aprovado novamente!");
        }
    }

    @Override
    public void excluirFeedback(Feedback feedback) {
        if(!feedback.isEXCLUIDA()) {
            feedback.setEXCLUIDA(true);
        }   else {
            throw new RuntimeException("Um feedbacck só pode ser excluido uma vez!");
        }
    }

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
    public void sairDaRepublica() {
        this.getUsuario().getRepublica().getEstado().sortearRepresentante();
        this.getUsuario().getRepublica().removerMorador(this.getUsuario());
        this.getUsuario().setUsuarioState(new EstadoSemTeto(this.getUsuario()));
        this.getUsuario().setRepublica(null);
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
    public void addMorador(Usuario usuario) {
        this.getUsuario().getRepublica().getEstado().addMorador(usuario);
        usuario.setUsuarioState(new EstadoMorador(usuario));
    }

    @Override
    public void removerMorador(Usuario usuario) {
        usuario.getRepublica().getEstado().removerMorador(usuario);
        usuario.setUsuarioState(new EstadoSemTeto(usuario));
    }
}
