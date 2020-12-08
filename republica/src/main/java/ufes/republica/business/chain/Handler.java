package ufes.republica.business.chain;

import ufes.republica.model.Usuario;

public abstract class Handler {

    private final Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    public abstract double calcula(Usuario usuario);

    public Handler getNext() {
        return next;
    }
}
