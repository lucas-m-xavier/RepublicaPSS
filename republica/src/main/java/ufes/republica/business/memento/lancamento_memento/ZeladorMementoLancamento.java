/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.memento.lancamento_memento;

import java.util.Stack;

/**
 *
 * @author 55289
 */
public class ZeladorMementoLancamento {

    private final Stack<MementoLancamento> elementos;

    public ZeladorMementoLancamento() {
        this.elementos = new Stack<>();
    }

    public void add(MementoLancamento mementoLancamento) {
        this.elementos.push(mementoLancamento);
    }

    public MementoLancamento getUltimo() throws Exception {
        if (!elementos.isEmpty()) {
            return elementos.pop();
        }
        throw new Exception("Não há estados salvos");
    }
}
