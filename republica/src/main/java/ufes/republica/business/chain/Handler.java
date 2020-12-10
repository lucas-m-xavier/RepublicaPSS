package ufes.republica.business.chain;

import ufes.republica.model.Feedback;
import ufes.republica.model.Lancamento;
import ufes.republica.model.Tarefa;

import java.util.ArrayList;

public abstract class Handler {

    private final Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    public abstract double calcula(ArrayList<Feedback> feedbacks, ArrayList<Tarefa> tarefas, ArrayList<Lancamento> lancamentos);

    public Handler getNext() {
        return next;
    }
}
