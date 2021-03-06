/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Lucas
 */
public class Feedback {

    private int id;
    
    private int idRepublica;

    private LocalDate dataCriacao;
    
    private String descricao;
    
    private LocalDate dataSolucao;

    private boolean EXCLUIDA;

    private boolean concluida;
    
    private long idade;
    
    private Usuario usuario;

    public Feedback() {
    }

    public Feedback(String descricao, Usuario usuario) {
        this.descricao = descricao;
        this.usuario = usuario;
        this.dataCriacao = LocalDate.now();
        this.dataSolucao = null;
        this.concluida = false;
    }

    public Feedback(int idRepublica,int id, LocalDate dataCriacao, String descricao, LocalDate dataSolucao, boolean EXCLUIDA, boolean concluida, Usuario usuario) {
        this.idRepublica = idRepublica;
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.dataSolucao = dataSolucao;
        this.EXCLUIDA = EXCLUIDA;
        this.concluida = concluida;
        this.idade = ChronoUnit.DAYS.between(dataSolucao, dataCriacao);
    }

    public LocalDate getDataSolucao() {
        return dataSolucao;
    }

    public long getIdade() {
        return idade;
    }

    public boolean isEXCLUIDA() {
        return EXCLUIDA;
    }

    public void setEXCLUIDA(boolean EXCLUIDA) {
        this.EXCLUIDA = EXCLUIDA;
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

    public boolean isConcluida() {
        return concluida;
    }
}
