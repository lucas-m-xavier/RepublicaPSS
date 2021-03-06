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
public class EstadoAberta extends RepublicaState {

    public EstadoAberta(Republica republica) {
        super(republica);
    }
    /*
    @Override
    public void extinguirRepublica() {
        this.getRepublica().setEstado(new EstadoExtinta(this.getRepublica()));

        for (Usuario usuario : this.getRepublica().getMoradores()) {
            this.removerMorador(usuario);
            usuario.getUsuarioState().sairDaRepublica();
        }
    }
    
    @Override
    public void removerMorador(Usuario usuario) {
        this.getRepublica().removerMorador(usuario);
        usuario.setUsuarioState(new EstadoSemTeto(usuario));
    }
    
    @Override
    public void addMorador(Usuario usuario) {
        this.getRepublica().addMorador(usuario);
        usuario.setUsuarioState(new EstadoMorador(usuario));

        if(this.getRepublica().getVagasDisponiveis() == 0) {
            this.getRepublica().setEstado(new EstadoLotada(this.getRepublica()));
        }
    }

    @Override
    public void sortearRepresentante() {
        if(this.getRepublica().getMoradores().size() == 1) {
            throw new RuntimeException("República com apenas o representante!");
        }

        for (Usuario usuario : this.getRepublica().getMoradores()) {
            if(usuario.getUsuarioState() instanceof EstadoMorador) {
                usuario.setUsuarioState(new EstadoRepresentante(usuario));
                break;
            }
        }
    }

     */
}
