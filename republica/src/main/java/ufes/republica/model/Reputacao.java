/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import ufes.republica.business.chain.Handler;
import ufes.republica.business.chain.ReputacaoSolucao;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class Reputacao {

    private int id;

    private double indice;
    
    private LocalDate data;
    
    private Usuario usuario;

    private Handler first = new ReputacaoSolucao();

    public Reputacao(Usuario usuario, ArrayList<Feedback> feedbacks, ArrayList<Tarefa> tarefas, ArrayList<Lancamento> lancamentos) {
        this.data = LocalDate.now();
        this.usuario = usuario;
        this.indice = first.calcula(feedbacks, tarefas, lancamentos);
    }

    public Reputacao(int id, double indice, LocalDate data, Usuario usuario) {
        this.id = id;
        this.indice = indice;
        this.data = data;
        this.usuario = usuario;
    }

    public LocalDate getData() {
        return data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getId() {
        return id;
    }

    public double getIndice(){
        return this.indice;
    }
}
