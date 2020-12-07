/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.enums;

/**
 *
 * @author 55289
 */
public enum EnumPeriodicidade {
   DESPESA_UNICA(0), SEMANAL(7), MENSAL(30);

    public int getValorPeriodicidade() {
        return valorPeriodicidade;
    }

    public int valorPeriodicidade;

    EnumPeriodicidade(int valor) {
        valorPeriodicidade = valor;
    } 
}
