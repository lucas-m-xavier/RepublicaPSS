package ufes.republica.business.chain;

import ufes.republica.business.state.feedback_state.EstadoConcluido;
import ufes.republica.model.Feedback;
import ufes.republica.model.Usuario;

public class ReputacaoSolucao extends Handler{

    public ReputacaoSolucao() {
        super(new ReputacaoTarefas());
    }

    @Override
    public double calcula(Usuario usuario) {
        double nrr = 0;
        double nr = 0;

        for(Feedback feedback : usuario.getFeedbacks()) {
            nr++;
            if(feedback.getEstado() instanceof EstadoConcluido) nrr++;
        }

        if(nr == 0) return this.getNext().calcula(usuario);

        return nrr/nr + this.getNext().calcula(usuario);
    }
}
