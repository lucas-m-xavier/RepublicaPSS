/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import ufes.republica.business.command.CommandMementoRepublica;
import ufes.republica.business.memento.republica_memento.MementoRepublica;
import ufes.republica.business.state.republica_state.EstadoAberta;
import ufes.republica.business.state.republica_state.RepublicaState;

/**
 *
 * @author Lucas
 */
public class Republica implements CommandMementoRepublica{

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

    public Republica(String nome, Endereco endereco, String vantagens, double despesasMedias, int vagasTotais, int vagasOcupadas, double saldoTotal, String codEtica) {
        this.nome = nome;
        this.endereco = endereco;
        this.vantagens = vantagens;
        this.despesasMedias = despesasMedias;
        this.vagasTotais = vagasTotais;
        this.vagasOcupadas = vagasOcupadas;
        this.saldoTotal = saldoTotal;
        this.codEtica = codEtica;
        this.fundacao = LocalDate.now();
        this.extincao = null;
        this.vagasDisponiveis = vagasTotais - vagasOcupadas;
        this.estado = new EstadoAberta(this);
    }

    @Override
    public MementoRepublica criar() {
        return new MementoRepublica(this.nome, this.fundacao, this.extincao, this.endereco, this.vantagens, this.despesasMedias, this.vagasTotais, this.vagasOcupadas, this.vagasDisponiveis, this.saldoTotal, this.codEtica, this.estado);
    }

    @Override
    public void restaurar(MementoRepublica mementoRepublica) {
        this.nome = mementoRepublica.getNome();
        this.endereco = mementoRepublica.getEndereco();
        this.vantagens = mementoRepublica.getVantagens();
        this.despesasMedias = mementoRepublica.getDespesasMedias();
        this.vagasTotais = mementoRepublica.getVagasTotais();
        this.vagasOcupadas = mementoRepublica.getVagasOcupadas();
        this.saldoTotal = mementoRepublica.getSaldoTotal();
        this.codEtica = mementoRepublica.getCodEtica();
        this.extincao = mementoRepublica.getExtincao();
        this.vagasDisponiveis = mementoRepublica.getVagasDisponiveis();
        this.estado = mementoRepublica.getEstado();
    }

    public void addMorador(Usuario morador) {
        if (morador == null) {
            throw new RuntimeException("Morador não pode ser nulo!");
        }

        if (this.vagasDisponiveis > 0) {
            Optional<Usuario> moradorEncontrado = getMoradorPorNome(morador.getNome());
            if (!moradorEncontrado.isPresent()) {
                moradores.add(morador);
                this.vagasDisponiveis--;
                this.vagasOcupadas++;
            } else {
                throw new RuntimeException("Morador já está em uma república");
            }
        } else {
            throw new RuntimeException("Republica cheia");
        }
    }

    public Optional<Usuario> getMoradorPorNome(String nome) {
        Optional<Usuario> moradorEncontrado = Optional.empty();
        for (Usuario morador : this.getMoradores()) {
            if (morador.getNome().toUpperCase().equals(nome.toUpperCase())) {
                moradorEncontrado = Optional.of(morador);
            }
        }
        return moradorEncontrado;
    }

    public void removerMorador(Usuario usuario) {
        Optional<Usuario> moradorEncontrado = getMoradorPorNome(usuario.getNome());
        if (!moradorEncontrado.isPresent()) {
            throw new RuntimeException("Morador " + /*nomeMorador +*/ " não encontrado!");
        }
        //HistoricoMorador historico = new HistoricoMorador(this);
        //moradorEncontrado.get().getHistorico().add(historico);
        this.getMoradores().remove(moradorEncontrado.get());
        this.vagasDisponiveis++;
        this.vagasOcupadas--;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getFundacao() {
        return fundacao;
    }

    public LocalDate getExtincao() {
        return extincao;
    }

    public void setExtincao(LocalDate extincao) {
        this.extincao = extincao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getVantagens() {
        return vantagens;
    }

    public void setVantagens(String vantagens) {
        this.vantagens = vantagens;
    }

    public double getDespesasMedias() {
        return despesasMedias;
    }

    public void setDespesasMedias(double despesasMedias) {
        this.despesasMedias = despesasMedias;
    }

    public int getVagasTotais() {
        return vagasTotais;
    }

    public void setVagasTotais(int vagasTotais) {
        this.vagasTotais = vagasTotais;
    }

    public int getVagasOcupadas() {
        return vagasOcupadas;
    }

    public void setVagasOcupadas(int vagasOcupadas) {
        this.vagasOcupadas = vagasOcupadas;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public String getCodEtica() {
        return codEtica;
    }

    public void setCodEtica(String codEtica) {
        this.codEtica = codEtica;
    }

    public ArrayList<Usuario> getMoradores() {
        return moradores;
    }

    public void setMoradores(ArrayList<Usuario> moradores) {
        this.moradores = moradores;
    }

    public ArrayList<Historico> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<Historico> historico) {
        this.historico = historico;
    }

    public RepublicaState getEstado() {
        return estado;
    }

    public void setEstado(RepublicaState estado) {
        this.estado = estado;
    }
}
