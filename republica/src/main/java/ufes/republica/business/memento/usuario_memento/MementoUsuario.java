/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.memento.usuario_memento;

/**
 *
 * @author 55289
 */
public class MementoUsuario {

    private String nome;

    private String apelido;

    private String telefone;

    private String cpf;

    private String sociais;

    private String responsavel1;

    private String responsavel2;

    public MementoUsuario(String nome, String apelido, String telefone, String cpf, String sociais,String responsavel1, String responsavel2) {
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

    public String getApelido() {
        return apelido;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSociais() {
        return sociais;
    }

    public String getResponsavel1() {
        return responsavel1;
    }

    public String getResponsavel2() {
        return responsavel2;
    }

}
