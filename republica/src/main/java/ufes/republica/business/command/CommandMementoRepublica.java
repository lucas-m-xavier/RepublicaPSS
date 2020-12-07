/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.command;

import ufes.republica.business.memento.republica_memento.MementoRepublica;

/**
 *
 * @author 55289
 */
public interface CommandMementoRepublica {

    public MementoRepublica criar();

    public void restaurar(MementoRepublica mementoRepublica);
}
