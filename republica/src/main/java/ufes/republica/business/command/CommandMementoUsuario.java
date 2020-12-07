/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.command;

import ufes.republica.business.memento.usuario_memento.MementoUsuario;

/**
 *
 * @author 55289
 */
public interface CommandMementoUsuario {

    public MementoUsuario criar();

    public void restaurar(MementoUsuario mementoUsuario);
}
