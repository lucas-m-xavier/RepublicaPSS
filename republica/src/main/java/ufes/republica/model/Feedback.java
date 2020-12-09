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

    private final LocalDate dataCriacao;
    
    private final String descricao;
    
    private LocalDate dataSolucao;
    
    private long idade;
    
    private final Usuario usuario;

    private boolean EXCLUIDA;
    
    private FeedbackState estado;

    public Feedback(String descricao, Usuario usuario) {
        this.descricao = descricao;
        this.usuario = usuario;
        this.dataCriacao = LocalDate.now();
        this.dataSolucao = null;
        this.estado = new EstadoEmAberto(this);
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

    public int getId() {
        return id;
    }
}
