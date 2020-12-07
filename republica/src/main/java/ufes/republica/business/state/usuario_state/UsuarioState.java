/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.state.usuario_state;

import ufes.republica.model.*;

/**
 *
 * @author 55289
 */
public abstract class UsuarioState {

    private Usuario usuario;

    public UsuarioState(Usuario usuario) {
        this.usuario = usuario;
    }

    public void criarRepublica(Republica republica) {
        throw new RuntimeException("Não é possível criar uma nova republica!");
    }

    public void aprovarLancamento(Lancamento lancamento) {
        throw new RuntimeException("Você não tem permissão para aprovar um lançamento");
    }

    public void excluirFeedback(Feedback feedback) {
        throw new RuntimeException("Você não tem permissão para excluir um feedback");
    }

    public void concluirFeedback(Feedback feedback) {
        throw new RuntimeException("Você não tem permissão para concluir um feedback");
    }

    public void finalizarTarefa(Tarefa tarefa) {
        throw new RuntimeException("Você não tem permissão para finalizar uma tarefa");
    }

    public void sairDaRepublica() {
        throw new RuntimeException("Você não está em uma república");
    }

    public void addMorador(Usuario usuario) {
        throw new RuntimeException("Apenas o representante pode adicionar um morador!");
    }

    public void removerMorador(Usuario usuario) {
        throw new RuntimeException("Apenas o representante pode remover um morador!");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
