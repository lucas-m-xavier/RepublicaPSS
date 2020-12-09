/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import ufes.republica.business.command.CommandMementoUsuario;
import ufes.republica.business.memento.usuario_memento.MementoUsuario;
import ufes.republica.business.state.usuario_state.UsuarioState;

/**
 *
 * @author Lucas
 */
public class Usuario implements CommandMementoUsuario{

    private int id;

    private String nome;

    private String apelido;

    private String telefone;

    private final String cpf;

    private String sociais;

    private String email;

    private String senha;

    private String responsavel1;

    private String responsavel2;

    private UsuarioState usuarioState;

    private Republica republica;

    private ArrayList<Reputacao> reputacao = new ArrayList<>();

    private ArrayList<Feedback> feedbacks = new ArrayList<>();

    private ArrayList<Tarefa> tarefas = new ArrayList<>();

    private ArrayList<Lancamento> lancamentos = new ArrayList<>();

    private ArrayList<Historico> historico = new ArrayList<>();

    public Usuario(String nome, String apelido, String telefone, String cpf, String sociais, String email, String senha, String responsavel1, String responsavel2) {
        this.nome = nome;
        this.apelido = apelido;
        this.telefone = telefone;
        this.cpf = cpf;
        this.sociais = sociais;
        this.email = email;
        this.senha = senha;
        this.responsavel1 = responsavel1;
        this.responsavel2 = responsavel2;
    }

    @Override
    public MementoUsuario criar() {
        return new MementoUsuario(this.nome, this.apelido, this.telefone, this.cpf, this.sociais, this.email, this.senha, this.responsavel1, this.responsavel2);
    }

    @Override
    public void restaurar(MementoUsuario mementoUsuario) {
        this.nome = mementoUsuario.getNome();
        this.apelido = mementoUsuario.getApelido();
        this.telefone = mementoUsuario.getTelefone();
        this.sociais = mementoUsuario.getSociais();
        this.email = mementoUsuario.getEmail();
        this.senha = mementoUsuario.getSenha();
        this.responsavel1 = mementoUsuario.getResponsavel1();
        this.responsavel2 = mementoUsuario.getResponsavel2();
    }

    public String getNome() {
        return nome;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<Historico> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<Historico> historico) {
        this.historico = historico;
    }

    public UsuarioState getUsuarioState() {
        return usuarioState;
    }

    public void setUsuarioState(UsuarioState usuarioState) {
        this.usuarioState = usuarioState;
    }

    public Republica getRepublica() {
        return republica;
    }

    public void setRepublica(Republica republica) {
        this.republica = republica;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Reputacao> getReputacao() {
        return reputacao;
    }

    public void setReputacao(ArrayList<Reputacao> reputacao) {
        this.reputacao = reputacao;
    }

    public ArrayList<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(ArrayList<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public ArrayList<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(ArrayList<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public ArrayList<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(ArrayList<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public int getId() {
        return id;
    }
}
