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
public class UsuarioLogin {
    
    private int id;
    
    private String email;
    
    private String senha;
    
    private Usuario usuario;

    public UsuarioLogin() {
    }

    public UsuarioLogin(int id, String email, String senha, Usuario usuario) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
    }

    public UsuarioLogin(String email, String senha, Usuario usuario) {
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
