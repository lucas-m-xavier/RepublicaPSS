/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import java.time.LocalDate;

import ufes.republica.business.command.CommandMementoLancamento;
import ufes.republica.business.memento.lancamento_memento.MementoLancamento;

/**
 *
 * @author Lucas
 */
public class Lancamento implements CommandMementoLancamento {

    private int id;

    private String descricao;

    private LocalDate dataVencimento;

    private double valor;

    private String periodicidade;

    private double valorParcela;

    private String tipo;

    private LocalDate dataPagamento;

    private Rateio rateio;

    public Lancamento(String descricao, LocalDate dataVencimento, double valor, String periodicidade, double valorParcela, String tipo, Rateio rateio) {
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.periodicidade = periodicidade;
        this.valorParcela = valorParcela;
        this.tipo = tipo;
        this.rateio = rateio;
    }

    public Lancamento(int id, String descricao, LocalDate dataVencimento, double valor, String periodicidade, double valorParcela, String tipo, LocalDate dataPagamento, Rateio rateio) {
        this.id = id;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.periodicidade = periodicidade;
        this.valorParcela = valorParcela;
        this.tipo = tipo;
        this.dataPagamento = dataPagamento;
        this.rateio = rateio;
    }

    public Lancamento() {
    }

    public MementoLancamento criar() {
        return new MementoLancamento(this.descricao, this.dataVencimento, this.valor, this.periodicidade, this.valorParcela, this.tipo, this.rateio);
    }

    public void restaurar(MementoLancamento mementoLancamento) {
        this.descricao = mementoLancamento.getDescricao();
        this.dataVencimento = mementoLancamento.getDataVencimento();
        this.valor = mementoLancamento.getValor();
        this.periodicidade = mementoLancamento.getPeriodicidade();
        this.valorParcela = mementoLancamento.getValorParcela();
        this.tipo = mementoLancamento.getTipo();
        this.rateio = mementoLancamento.getRateio();
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

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getId() {
        return id;
    }
}
