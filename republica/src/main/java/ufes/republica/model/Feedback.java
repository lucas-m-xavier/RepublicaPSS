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
    private final LocalDate dataCriacao;
    
    private final String descricao;
    
    private LocalDate dataSolucao;
    
    private long idade;
    //LIST DE USUARIOS????
    private final Usuario usuario;

    public Feedback(String descricao, Usuario usuario) {
        this.descricao = descricao;
        this.usuario = usuario;
        this.dataCriacao = LocalDate.now();
        this.dataSolucao = null;
    }
    
    public final void concluirFeedback() {
        //MUDAR ESTADO
        this.dataSolucao = LocalDate.now();
        this.idade = ChronoUnit.DAYS.between(dataSolucao, dataCriacao);
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataSolucao() {
        return dataSolucao;
    }

    public long getIdade() {
        return idade;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
