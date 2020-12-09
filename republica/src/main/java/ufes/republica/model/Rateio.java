/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class Rateio {

    private int id;

    private final ArrayList<Double> valor;
    
    private final ArrayList<Usuario> moradores;

    public Rateio(ArrayList<Double> valor, ArrayList<Usuario> moradores) {
        this.valor = valor;
        this.moradores = moradores;
    }

    public ArrayList<Double> getValor() {
        return valor;
    }

    public ArrayList<Usuario> getMoradores() {
        return moradores;
    }

    public int getId() {
        return id;
    }
}
