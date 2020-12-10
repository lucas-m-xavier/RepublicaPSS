/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import ufes.republica.business.command.CommandMementoUsuario;
import ufes.republica.business.memento.usuario_memento.MementoUsuario;
import ufes.republica.business.state.usuario_state.UsuarioState;

/**
 *
 * @author Lucas
 */
public class Usuario implements CommandMementoUsuario{

    private int id;

    private String cpf;

    private String nome;

    private String apelido;

    private String telefone;

    private String sociais;

    private String responsavel1;

    private String responsavel2;

    private UsuarioState usuarioState;

    private Reputacao reputacao;

    private Feedback feedbacks;

    private Tarefa tarefas;

    private Historico historico;

    private Rateio rateio;
    
    private UsuarioLogin usuarioLogin;

    public Usuario() {
    }

    public Usuario(int id, String cpf, String nome, String apelido, String telefone, String sociais, String responsavel1,
                   String responsavel2, UsuarioState usuarioState, Reputacao reputacao,
                   Feedback feedbacks, Tarefa tarefas, Historico historico, Rateio rateio) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.apelido = apelido;
        this.telefone = telefone;
        this.sociais = sociais;
        this.responsavel1 = responsavel1;
        this.responsavel2 = responsavel2;
        this.usuarioState = usuarioState;
        this.reputacao = reputacao;
        this.feedbacks = feedbacks;
        this.tarefas = tarefas;
        this.historico = historico;
        this.rateio = rateio;
    }

    public Usuario(String cpf, String nome, String apelido, String telefone, String sociais, String responsavel1, String responsavel2,
                   UsuarioState usuarioState, Reputacao reputacao, Feedback feedbacks, Tarefa tarefas, Historico historico, Rateio rateio) {
        this.cpf = cpf;
        this.nome = nome;
        this.apelido = apelido;
        this.telefone = telefone;
        this.sociais = sociais;
        this.responsavel1 = responsavel1;
        this.responsavel2 = responsavel2;
        this.usuarioState = usuarioState;
        this.reputacao = reputacao;
        this.feedbacks = feedbacks;
        this.tarefas = tarefas;
        this.historico = historico;
        this.rateio = rateio;
    }

    @Override
    public MementoUsuario criar() {
        return new MementoUsuario(this.nome, this.apelido, this.telefone, this.cpf, this.sociais, this.responsavel1, this.responsavel2);
    }

    @Override
    public void restaurar(MementoUsuario mementoUsuario) {
        this.nome = mementoUsuario.getNome();
        this.apelido = mementoUsuario.getApelido();
        this.telefone = mementoUsuario.getTelefone();
        this.sociais = mementoUsuario.getSociais();
        this.responsavel1 = mementoUsuario.getResponsavel1();
        this.responsavel2 = mementoUsuario.getResponsavel2();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UsuarioState getUsuarioState() {
        return usuarioState;
    }

    public void setUsuarioState(UsuarioState usuarioState) {
        this.usuarioState = usuarioState;
    }

    public Reputacao getReputacao() {
        return reputacao;
    }

    public void setReputacao(Reputacao reputacao) {
        this.reputacao = reputacao;
    }

    public Feedback getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Feedback feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Tarefa getTarefas() {
        return tarefas;
    }

    public void setTarefas(Tarefa tarefas) {
        this.tarefas = tarefas;
    }

    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public String getCpf() {
        return cpf;
    }
}
