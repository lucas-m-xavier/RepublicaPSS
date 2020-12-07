/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.memento.republica_memento;

import java.time.LocalDate;
import java.util.ArrayList;
import ufes.republica.business.state.republica_state.RepublicaState;
import ufes.republica.model.Endereco;
import ufes.republica.model.Historico;
import ufes.republica.model.Usuario;

/**
 *
 * @author 55289
 */
public class MementoRepublica {

    private String nome;
    private final LocalDate fundacao;
    private LocalDate extincao;
    private Endereco endereco;
    private String vantagens;
    private double despesasMedias;
    private int vagasTotais;
    private int vagasOcupadas;
    private int vagasDisponiveis;
    private double saldoTotal;
    private String codEtica;
    private RepublicaState estado;
    private ArrayList<Usuario> moradores = new ArrayList<>();
    private ArrayList<Historico> historico = new ArrayList<>();

    public MementoRepublica(String nome, LocalDate fundacao, LocalDate extincao, Endereco endereco, String vantagens, double despesasMedias, int vagasTotais, int vagasOcupadas, int vagasDisponiveis, double saldoTotal, String codEtica, RepublicaState estado) {
        this.nome = nome;
        this.fundacao = fundacao;
        this.extincao = extincao;
        this.endereco = endereco;
        this.vantagens = vantagens;
        this.despesasMedias = despesasMedias;
        this.vagasTotais = vagasTotais;
        this.vagasOcupadas = vagasOcupadas;
        this.vagasDisponiveis = vagasDisponiveis;
        this.saldoTotal = saldoTotal;
        this.codEtica = codEtica;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getFundacao() {
        return fundacao;
    }

    public LocalDate getExtincao() {
        return extincao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getVantagens() {
        return vantagens;
    }

    public double getDespesasMedias() {
        return despesasMedias;
    }

    public int getVagasTotais() {
        return vagasTotais;
    }

    public int getVagasOcupadas() {
        return vagasOcupadas;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public String getCodEtica() {
        return codEtica;
    }

    public RepublicaState getEstado() {
        return estado;
    }

    public ArrayList<Usuario> getMoradores() {
        return moradores;
    }

    public ArrayList<Historico> getHistorico() {
        return historico;
    }
    
    
}
