/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import ufes.republica.business.state.feedback_state.EstadoEmAberto;
import ufes.republica.business.state.feedback_state.FeedbackState;

/**
 *
 * @author Lucas
 */
public class Feedback {

    private int id;

    private LocalDate dataCriacao;
    
    private String descricao;
    
    private LocalDate dataSolucao;
    
    private long idade;
    
    private Usuario usuario;

    private boolean EXCLUIDA;
    
    private FeedbackState estado;

    public Feedback() {
    }

    public Feedback(String descricao, Usuario usuario) {
        this.descricao = descricao;
        this.usuario = usuario;
        this.dataCriacao = LocalDate.now();
        this.dataSolucao = null;
        this.estado = new EstadoEmAberto(this);
    }

    public Feedback(int id, LocalDate dataCriacao, String descricao, LocalDate dataSolucao, Usuario usuario, boolean EXCLUIDA, FeedbackState estado) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.dataSolucao = dataSolucao;
        this.usuario = usuario;
        this.EXCLUIDA = EXCLUIDA;
        this.estado = estado;
        this.idade = ChronoUnit.DAYS.between(dataSolucao, dataCriacao);
    }

    public LocalDate getDataSolucao() {
        return dataSolucao;
    }

    public void setDataSolucao(LocalDate dataSolucao) {
        this.dataSolucao = dataSolucao;
    }

    public long getIdade() {
        return idade;
    }

    public void setIdade(long idade) {
        this.idade = idade;
    }

    public boolean isEXCLUIDA() {
        return EXCLUIDA;
    }

    public void setEXCLUIDA(boolean EXCLUIDA) {
        this.EXCLUIDA = EXCLUIDA;
    }

    public FeedbackState getEstado() {
        return estado;
    }

    public void setEstado(FeedbackState estado) {
        this.estado = estado;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
}
