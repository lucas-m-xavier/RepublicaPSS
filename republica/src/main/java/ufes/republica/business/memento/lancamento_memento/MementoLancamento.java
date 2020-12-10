/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.memento.lancamento_memento;

import java.time.LocalDate;
import ufes.republica.business.state.lancamento_state.LancamentoState;
import ufes.republica.model.Rateio;

/**
 *
 * @author 55289
 */
public class MementoLancamento {

    private String descricao;

    private LocalDate dataVencimento;

    private double valor;

    private String periodicidade;

    private double valorParcela;

    private String tipo;

    private Rateio rateio;

    public MementoLancamento(String descricao, LocalDate dataVencimento, double valor, String periodicidade, double valorParcela, String tipo, Rateio rateio) {
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.periodicidade = periodicidade;
        this.valorParcela = valorParcela;
        this.tipo = tipo;
        this.rateio = rateio;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public double getValor() {
        return valor;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public String getTipo() {
        return tipo;
    }

    public Rateio getRateio() {
        return rateio;
    }

}
