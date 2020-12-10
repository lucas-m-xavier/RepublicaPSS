package ufes.republica.business.command;

import ufes.republica.business.memento.lancamento_memento.MementoLancamento;

public interface CommandMementoLancamento {

    public MementoLancamento criar();

    public void restaurar(MementoLancamento mementoLancamento);

}
