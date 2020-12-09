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
public class Historico {

    private int id;

    private final LocalDate dataSaida;
    
    private final String nomeRepresentante;
    
    private final double mediaReputacao;
    
    private final String nomeRepublica;
    
    private final Usuario usuario;
    
    private final Republica republica;

    public Historico(String nomeRepresentante, double mediaReputacao, String nomeRepublica, Usuario usuario, Republica republica) {
        this.dataSaida = LocalDate.now();
        this.nomeRepresentante = nomeRepresentante;
        this.mediaReputacao = mediaReputacao;//media na republica
        this.nomeRepublica = republica.getNome();
        this.usuario = usuario;
        this.republica = republica;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public String getNomeRepresentante() {
        return nomeRepresentante;
    }

    public double getMediaReputacao() {
        return mediaReputacao;
    }

    public String getNomeRepublica() {
        return nomeRepublica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Republica getRepublica() {
        return republica;
    }

    public int getId() {
        return id;
    }
}
