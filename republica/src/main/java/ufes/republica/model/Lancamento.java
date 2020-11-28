/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import java.time.LocalDate;

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

    public Lancamento(String descricao, LocalDate dataVencimento, double valor, String periodicidade, double valorParcela, String tipo, Rateio rateio) {
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
}
