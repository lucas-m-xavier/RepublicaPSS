/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.memento.republica_memento;

/**
 *
 * @author 55289
 */
import java.util.Stack;

/**
 *
 * @author 55289
 */
public class ZeladorMementoRepublica {

    private final Stack<MementoRepublica> elementos;

    public ZeladorMementoRepublica() {
        this.elementos = new Stack<>();
    }

    public void add(MementoRepublica mementoRepublica) {
        this.elementos.push(mementoRepublica);
    }

    public MementoRepublica getUltimo() throws Exception {
        if (!elementos.isEmpty()) {
            return elementos.pop();
        }
        throw new Exception("Não há estados salvos");
    }
}
