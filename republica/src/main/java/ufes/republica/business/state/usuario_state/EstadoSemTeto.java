/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.state.usuario_state;

import ufes.republica.business.state.republica_state.EstadoAberta;
import ufes.republica.model.Republica;
import ufes.republica.model.Usuario;

/**
 *
 * @author 55289
 */
public class EstadoSemTeto extends UsuarioState {

    public EstadoSemTeto(Usuario usuario) {
        super(usuario);
    }
    /*
    @Override
    public void criarRepublica(Republica republica) {
        this.getUsuario().setUsuarioState(new EstadoRepresentante(this.getUsuario()));
        this.getUsuario().setRepublica(republica);
        republica.setEstado(new EstadoAberta(republica));
        republica.addMorador(this.getUsuario());
    }
    */
}
