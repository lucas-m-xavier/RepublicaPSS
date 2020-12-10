/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;


/**
 *
 * @author Lucas
 */
public class Rateio {

    private int id;

    private double valor;
    
    private Usuario morador;

    public Rateio() {
    }

    public Rateio(double valor, Usuario morador) {
        this.valor = valor;
        this.morador = morador;
    }

    public Rateio(int id, double valor, Usuario morador) {
        this.id = id;
        this.valor = valor;
        this.morador = morador;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public Usuario getMorador() {
        return morador;
    }
}
