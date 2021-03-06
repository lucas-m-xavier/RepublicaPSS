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

    private LocalDate dataSaida;
    
    private LocalDate dataEntrada;    
    
    private String nomeRepresentante;
    
    private double mediaReputacao;
    
    private String nomeRepublica;
    
    private Usuario usuario;
    
    private Republica republica;

    public Historico() {
    }

    public Historico(int id, LocalDate dataSaida, String nomeRepresentante, double mediaReputacao, String nomeRepublica, Usuario usuario, Republica republica) {
        this.id = id;
        this.dataSaida = dataSaida;
        this.nomeRepresentante = nomeRepresentante;
        this.mediaReputacao = mediaReputacao;
        this.nomeRepublica = nomeRepublica;
        this.usuario = usuario;
        this.republica = republica;
    }

    public Historico(String nomeRepresentante, double mediaReputacao, String nomeRepublica, Usuario usuario, Republica republica) {
        this.dataSaida = LocalDate.now();
        this.nomeRepresentante = nomeRepresentante;
        this.mediaReputacao = mediaReputacao;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public void setNomeRepresentante(String nomeRepresentante) {
        this.nomeRepresentante = nomeRepresentante;
    }

    public void setMediaReputacao(double mediaReputacao) {
        this.mediaReputacao = mediaReputacao;
    }

    public void setNomeRepublica(String nomeRepublica) {
        this.nomeRepublica = nomeRepublica;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setRepublica(Republica republica) {
        this.republica = republica;
    }
    
}
