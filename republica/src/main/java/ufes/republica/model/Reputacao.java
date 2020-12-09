/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import ufes.republica.business.chain.Handler;
import ufes.republica.business.chain.ReputacaoSolucao;

import java.time.LocalDate;

/**
 *
 * @author Lucas
 */
public class Reputacao {

    private int id;

    private double indice;
    
    private final LocalDate data;
    
    private final Usuario usuario;

    private Handler first = new ReputacaoSolucao();

    public Reputacao(Usuario usuario) {
        this.data = LocalDate.now();
        this.usuario = usuario;
        this.calculaIndice();
    }

    public Reputacao(int id, double indice, LocalDate data, Usuario usuario) {
        this.id = id;
        this.indice = indice;
        this.data = data;
        this.usuario = usuario;
    }
    
    
    public final void calculaIndice() {
        this.indice = first.calcula(this.usuario);
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
