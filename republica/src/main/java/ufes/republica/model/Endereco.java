/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import ufes.republica.enums.EnumUF;

/**
 *
 * @author Lucas
 */
public class Endereco {

    private int id;

    private String cep;
    
    private String bairro;

    private String logradouro;

    private int numero;
    
    private String referencia;
    
    private EnumUF uf;
    
    private GeoLocalizacao geoLocalizacao;

    public Endereco(int id, String cep, String bairro, String logradouro, int numero, String referencia, EnumUF uf, GeoLocalizacao geoLocalizacao) {
        this.id = id;
        this.cep = cep;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.referencia = referencia;
        this.uf = uf;
        this.geoLocalizacao = geoLocalizacao;
    }

    public Endereco() {
    }

    public int getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getReferencia() {
        return referencia;
    }

    public EnumUF getUf() {
        return uf;
    }

    public GeoLocalizacao getGeoLocalizacao() {
        return geoLocalizacao;
    }
}
