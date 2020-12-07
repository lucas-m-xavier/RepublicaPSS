/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.memento.usuario_memento;

import java.util.Stack;

/**
 *
 * @author 55289
 */
public class ZeladorMementoUsuario {

    private final Stack<MementoUsuario> elementos;

    public ZeladorMementoUsuario() {
        this.elementos = new Stack<>();
    }

    public void add(MementoUsuario mementoUsuario) {
        this.elementos.push(mementoUsuario);
    }

    public MementoUsuario getUltimo() throws Exception {
        if (!elementos.isEmpty()) {
            return elementos.pop();
        }
        throw new Exception("Não há estados salvos");
    }
}
