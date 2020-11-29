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
public abstract class RepublicaState {
    private Republica republica;
    
    public RepublicaState(Republica republica) {
        this.republica = republica;
    }
    
    public void extinguirRepublica() {
        throw new RuntimeException("Uma república já extinta não pode ser extinta novamente!");
    }
    
    public void addMorador(Usuario usuario) {
        throw new RuntimeException("Republica extinta ou lotada!");
    }
    
    public void removerMorador(Usuario usuario) {
        throw new RuntimeException("Uma república já extinta não pode remover moradores!");
    }

    public Republica getRepublica() {
        return republica;
    }
}
