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
public class Usuario {
    private String nome;
    
    private String apelido;
    
    private String telefone;
    
    private String cpf;
    
    private String sociais;
    
    private String responsavel1;
    
    private String responsavel2;

    public Usuario() {
    }

    public Usuario(String nome, String apelido, String telefone, String cpf, String sociais, String responsavel1, String responsavel2) {
        this.nome = nome;
        this.apelido = apelido;
        this.telefone = telefone;
        this.cpf = cpf;
        this.sociais = sociais;
        this.responsavel1 = responsavel1;
        this.responsavel2 = responsavel2;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSociais() {
        return sociais;
    }

    public void setSociais(String sociais) {
        this.sociais = sociais;
    }

    public String getResponsavel1() {
        return responsavel1;
    }

    public void setResponsavel1(String responsavel1) {
        this.responsavel1 = responsavel1;
    }

    public String getResponsavel2() {
        return responsavel2;
    }

    public void setResponsavel2(String responsavel2) {
        this.responsavel2 = responsavel2;
    }
}
