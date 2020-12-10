package ufes.republica.business.chain;

import ufes.republica.model.Feedback;
import ufes.republica.model.Lancamento;
import ufes.republica.model.Tarefa;

import java.util.ArrayList;

public class ReputacaoTarefas extends Handler{

    public ReputacaoTarefas() {
        super(new ReputacaoPagamentos());
    }

    @Override
    public double calcula(ArrayList<Feedback> feedbacks, ArrayList<Tarefa> tarefas, ArrayList<Lancamento> lancamentos) {
        double tcm = 0;
        double tam = 0;

        for(Tarefa tarefa : tarefas) {
            tam++;
            if(tarefa.isFinalizada()) tcm++;
        }

        if(tam == 0) return this.getNext().calcula(feedbacks, tarefas, lancamentos);

        return tcm/tam + this.getNext().calcula(feedbacks, tarefas, lancamentos);
    }
}
