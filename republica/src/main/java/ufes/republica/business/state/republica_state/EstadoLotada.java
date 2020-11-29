/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.state.republica_state;

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
    
    @Override
    public void extinguirRepublica() {
        this.getRepublica().setEstado(new EstadoExtinta(this.getRepublica()));
    }
    
    @Override
    public void removerMorador(Usuario usuario) {
        this.getRepublica().removerMorador(usuario);
    }
}
