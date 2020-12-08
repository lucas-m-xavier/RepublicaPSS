package ufes.republica.business.chain;

import ufes.republica.business.state.tarefa_state.EstadoFinalizada;
import ufes.republica.model.Tarefa;
import ufes.republica.model.Usuario;

public class ReputacaoTarefas extends Handler{

    public ReputacaoTarefas() {
        super(new ReputacaoPagamentos());
    }

    @Override
    public double calcula(Usuario usuario) {
        double tcm = 0;
        double tam = 0;

        for(Tarefa tarefa : usuario.getTarefas()) {
            tam++;
            if(tarefa.getEstado() instanceof EstadoFinalizada) tcm++;
        }

        if(tam == 0) return this.getNext().calcula(usuario);

        return tcm/tam + this.getNext().calcula(usuario);
    }
}
