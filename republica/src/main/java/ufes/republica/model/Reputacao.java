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
public class Reputacao {
    private double indice;
    
    private final LocalDate data;
    
    private final Usuario usuario;

    public Reputacao(Usuario usuario) {
        this.data = LocalDate.now();
        this.usuario = usuario;
        this.calculaIndice();
    }
    
    public final void calculaIndice() {
        //CHAIN
    } 

    public double getIndice() {
        return indice;
    }

    public LocalDate getData() {
        return data;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
