/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.state.usuario_state;

import ufes.republica.model.Usuario;

/**
 *
 * @author 55289
 */
public abstract class UsuarioState {

    private Usuario usuario;

    public void criarRepublica() {
        throw new RuntimeException("Não é possível criar uma nova republica!");
    }

    public UsuarioState(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
