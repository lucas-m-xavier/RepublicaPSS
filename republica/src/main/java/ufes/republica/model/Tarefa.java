/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import java.time.LocalDate;

import ufes.republica.business.state.tarefa_state.EstadoPendente;
import ufes.republica.business.state.tarefa_state.TarefaState;

/**
 *
 * @author Lucas
 */
public class Tarefa {

    private int id;

    private final LocalDate dataAgendamento;
    
    private String descricao;
    
    private LocalDate dataTermino;
    
    private Usuario usuario;
    
    private TarefaState estado;

    public Tarefa(String descricao, Usuario usuario) {
        this.dataAgendamento = LocalDate.now();
        this.descricao = descricao;
        this.usuario = usuario;
        this.estado = new EstadoPendente(this);
    }

    public Tarefa(LocalDate dataAgendamento, String descricao, LocalDate dataTermino, Usuario usuario) {
        this.dataAgendamento = dataAgendamento;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.usuario = usuario;
        this.estado = new EstadoPendente(this);
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TarefaState getEstado() {
        return estado;
    }

    public void setEstado(TarefaState estado) {
        this.estado = estado;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public int getId() {
        return id;
    }
}
