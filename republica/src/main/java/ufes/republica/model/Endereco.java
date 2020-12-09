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

    private final String cep;
    
    private final String bairro;
    
    private final String referencia;
    
    private final int numero;
    
    private final String logradouro;
    
    private final EnumUF uf;
    
    private final GeoLocalizacao geoLocalizacao;

    public Endereco(int id, String cep, String bairro, String referencia, int numero, String logradouro, EnumUF uf, GeoLocalizacao geoLocalizacao) {
        this.id = id;
        this.cep = cep;
        this.bairro = bairro;
        this.referencia = referencia;
        this.numero = numero;
        this.logradouro = logradouro;
        this.uf = uf;
        this.geoLocalizacao = geoLocalizacao;
    }

    public Endereco(String cep, String bairro, String referencia, int numero, String logradouro, EnumUF uf, GeoLocalizacao geoLocalizacao) {
        this.cep = cep;
        this.bairro = bairro;
        this.referencia = referencia;
        this.numero = numero;
        this.logradouro = logradouro;
        this.uf = uf;
        this.geoLocalizacao = geoLocalizacao;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getReferencia() {
        return referencia;
    }

    public int getNumero() {
        return numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public EnumUF getUf() {
        return uf;
    }

    public GeoLocalizacao getGeoLocalizacao() {
        return geoLocalizacao;
    }

    public int getId() {
        return id;
    }
}
