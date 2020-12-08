package ufes.republica.business.chain;

import ufes.republica.model.Lancamento;
import ufes.republica.model.Usuario;

public class ReputacaoPagamentos extends Handler{

    public ReputacaoPagamentos() {
        super(null);
    }

    @Override
    public double calcula(Usuario usuario) {
        double icp = 0;

        for(Lancamento lancamento : usuario.getLancamentos()) {
            icp += lancamento.getDataVencimento().getDayOfMonth() / lancamento.getDataPagamento().getDayOfMonth();
        }

        return icp;
    }
}
