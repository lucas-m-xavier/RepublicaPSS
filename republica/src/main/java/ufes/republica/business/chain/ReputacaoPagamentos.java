package ufes.republica.business.chain;

import ufes.republica.model.Feedback;
import ufes.republica.model.Lancamento;
import ufes.republica.model.Tarefa;

import java.util.ArrayList;
import ufes.republica.model.Usuario;

public class ReputacaoPagamentos extends Handler{

    public ReputacaoPagamentos() {
        super(null);
    }

    @Override
    public double calcula(ArrayList<Feedback> feedbacks, ArrayList<Tarefa> tarefas, ArrayList<Lancamento> lancamentos) {
        double icp = 0;

        for(Lancamento lancamento : lancamentos) {
            icp += lancamento.getDataVencimento().getDayOfMonth() / lancamento.getDataPagamento().getDayOfMonth();
        }

        return icp;
    }

   
}
