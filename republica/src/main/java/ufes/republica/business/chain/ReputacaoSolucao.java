package ufes.republica.business.chain;

import ufes.republica.model.Feedback;
import ufes.republica.model.Lancamento;
import ufes.republica.model.Tarefa;

import java.util.ArrayList;

public class ReputacaoSolucao extends Handler{

    public ReputacaoSolucao() {
        super(new ReputacaoTarefas());
    }

    @Override
    public double calcula(ArrayList<Feedback> feedbacks, ArrayList<Tarefa> tarefas, ArrayList<Lancamento> lancamentos) {
        double nrr = 0;
        double nr = 0;

        for(Feedback feedback : feedbacks) {
            nr++;
            if(feedback.isConcluida()) nrr++;
        }

        if(nr == 0) return this.getNext().calcula(feedbacks, tarefas, lancamentos);

        return nrr/nr + this.getNext().calcula(feedbacks, tarefas, lancamentos);
    }
}
