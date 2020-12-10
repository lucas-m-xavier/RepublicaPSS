/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.state.republica_state;

import ufes.republica.business.state.usuario_state.EstadoMorador;
import ufes.republica.business.state.usuario_state.EstadoRepresentante;
import ufes.republica.business.state.usuario_state.EstadoSemTeto;
import ufes.republica.model.Republica;
import ufes.republica.model.Usuario;

/**
 *
 * @author Lucas
 */
public class EstadoLotada extends RepublicaState {

    public EstadoLotada(Republica republica) {
        super(republica);
    }
    /*
    @Override
    public void extinguirRepublica() {
        for (Usuario usuario : this.getRepublica().getMoradores()) {
            this.removerMorador(usuario);
            usuario.getUsuarioState().sairDaRepublica();
        }
        this.getRepublica().setEstado(new EstadoExtinta(this.getRepublica()));
    }
    
    @Override
    public void removerMorador(Usuario usuario) {
        this.getRepublica().removerMorador(usuario);
        usuario.setUsuarioState(new EstadoSemTeto(usuario));
        this.getRepublica().setEstado(new EstadoAberta(this.getRepublica()));
    }

    @Override
    public void sortearRepresentante() {
        for (Usuario usuario : this.getRepublica().getMoradores()) {
            if(usuario.getUsuarioState() instanceof EstadoMorador) {
                usuario.setUsuarioState(new EstadoRepresentante(usuario));
                break;
            }
        }
    }

     */
}
