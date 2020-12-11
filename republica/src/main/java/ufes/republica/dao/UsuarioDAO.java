/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.dao;

import ufes.republica.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import ufes.republica.business.state.usuario_state.EstadoMorador;
import ufes.republica.business.state.usuario_state.EstadoRepresentante;
import ufes.republica.business.state.usuario_state.EstadoSemTeto;
import ufes.republica.business.state.usuario_state.UsuarioState;

/**
 *
 * @author Lucas
 */
public class UsuarioDAO {
    
    private Connection conn;
    
    public UsuarioDAO() throws Exception {
        try {
            this.conn = Conexao.getConexao();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    public Usuario procurarUsuario(int id) throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from Usuario where usuario.idUsuario = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id );
            }
            
            Usuario usuario = new Usuario();
            usuario.setCpf(rs.getString(2));
            usuario.setNome(rs.getString(3));
            usuario.setApelido(rs.getString(4));
            usuario.setTelefone(rs.getString(5));
            usuario.setSociais(rs.getString(6));
            usuario.setResponsavel1(rs.getString(7));
            usuario.setResponsavel2(rs.getString(8));
            String estado = rs.getString(9);
            
            UsuarioState usuarioState = new EstadoSemTeto(usuario);
        
            if(estado.equalsIgnoreCase("Representate")) usuarioState = new EstadoRepresentante(usuario);
            if(estado.equalsIgnoreCase("Morador")) usuarioState = new EstadoMorador(usuario);
           
            usuario.setUsuarioState(usuarioState);

            return usuario;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            rs.close();
            ps.close();
        }
    }
    
    public void salvar(Usuario usuario) throws Exception {
        PreparedStatement ps = null;

        if (usuario == null) {
            throw new Exception("Usuario não pode ser nulo!");
        }
        try {
            String SQL = "INSERT INTO usuario (nome, apelido, telefone, cpf, linkSociais, responsavelUm,"
                    + " responsavelDois) values (?, ?, ?, ?, ?, ?, ?);";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getApelido());
            ps.setString(3, usuario.getTelefone());
            ps.setString(4, usuario.getCpf());
            ps.setString(5, usuario.getSociais());   
            ps.setString(6, usuario.getResponsavel1());
            ps.setString(7, usuario.getResponsavel2());
            ps.executeUpdate();
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }

    public void atualizar(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        
        if (usuario == null) {
            throw new Exception("Usuario não pode ser nulo!");
        }
        try {
            String SQL = "UPDATE usuario SET nome=?, apelido=?, telefone=?, cpf=?, linksociais=?,"
                    + "responsavelUm=?, responsavelDois=?"
                    + "where idUsuario = ?;";
            
            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getApelido());
            ps.setString(3, usuario.getTelefone());
            ps.setString(4, usuario.getCpf());
            ps.setString(5, usuario.getSociais());
            ps.setString(6, usuario.getResponsavel1());
            ps.setString(7, usuario.getResponsavel2());
            ps.setInt(8, usuario.getId());
            ps.executeUpdate();
            
        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }
        
    public void excluir(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        
        if (usuario == null) {
            throw new Exception("Usuario não pode ser nulo!");
        }
        try {            
            ps = conn.prepareStatement("delete from usuario where idUsuario = ?");
            ps.setInt(1, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }  
}
