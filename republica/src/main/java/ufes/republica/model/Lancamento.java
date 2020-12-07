/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import java.time.LocalDate;
import ufes.republica.business.memento.lancamento_memento.MementoLancamento;
import ufes.republica.business.state.lancamento_state.EstadoIndeferido;
import ufes.republica.business.state.lancamento_state.LancamentoState;

/**
 *
 * @author Lucas
 */
public class Lancamento {

    private String descricao;

    private LocalDate dataVencimento;

    private double valor;

    private String periodicidade;

    private double valorParcela;

    private String tipo;

    private Rateio rateio;

    private LancamentoState estado;

    public Lancamento(String descricao, LocalDate dataVencimento, double valor, String periodicidade, double valorParcela, String tipo, Rateio rateio) {
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.periodicidade = periodicidade;
        this.valorParcela = valorParcela;
        this.tipo = tipo;
        this.rateio = rateio;
        this.estado = new EstadoIndeferido(this);
    }

    public MementoLancamento criar() {
        return new MementoLancamento(this.descricao, this.dataVencimento, this.valor, this.periodicidade, this.valorParcela, this.tipo, this.rateio, this.estado);
    }

    public void restaurar(MementoLancamento mementoLancamento) {
        this.descricao = mementoLancamento.getDescricao();
        this.dataVencimento = mementoLancamento.getDataVencimento();
        this.valor = mementoLancamento.getValor();
        this.periodicidade = mementoLancamento.getPeriodicidade();
        this.valorParcela = mementoLancamento.getValorParcela();
        this.tipo = mementoLancamento.getTipo();
        this.rateio = mementoLancamento.getRateio();
        this.estado = mementoLancamento.getEstado();
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setRateio(Rateio rateio) {
        this.rateio = rateio;
    }

    public LancamentoState getEstado() {
        return estado;
    }

    public void setEstado(LancamentoState estado) {
        this.estado = estado;
    }
}
