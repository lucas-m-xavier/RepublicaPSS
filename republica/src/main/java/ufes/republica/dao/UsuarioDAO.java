/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.dao;

import ufes.republica.model.GeoLocalizacao;
import ufes.republica.model.Tarefa;
import ufes.republica.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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

    //PRA FAZER
    public Usuario procurarUsuario(int id) throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from Tarefa where idTarefa = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id );
            }

            LocalDate dataAgendamento = LocalDate.parse(rs.getDate(4).toString());
            String descricao = rs.getString(3);
            LocalDate dataTermino = LocalDate.parse(rs.getDate(5).toString());

            ps = conn.prepareStatement("select idUsuario from usuario INNER JOIN usuarioTarefa where idTarefa = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id );
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            Usuario usuario = usuarioDAO.procurarUsuario(rs.getInt(2));

            return new GeoLocalizacao(id,dataAgendamento, descricao, dataTermino, usuario, ESTADO);

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            rs.close();
            ps.close();
        }
    }

    public boolean procurarUsuario(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        
        ResultSet rs = null;
        try {            
            ps = conn.prepareStatement("select * from usuarios where id = ? and senha = ?");
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            rs = ps.executeQuery();
            if (!rs.next()) {                                
                return false;                
            }
            else
            {
                return true;            
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
            
        } finally {
            Conexao.fecharConexao(conn, ps, rs);
        }
    }
    
    public void salvar(Usuario usuario) throws Exception {
        PreparedStatement ps = null;

        if (usuario == null) {
            throw new Exception("Usuario não pode ser nulo!");
        }
        try {
            String SQL = "INSERT INTO usuario (nome, apelido, telefone, cpf, sociais, email, senha, responsavel1,"
                    + " responsavel2) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getApelido());
            ps.setString(3, usuario.getTelefone());
            ps.setString(4, usuario.getCpf());
            ps.setString(5, usuario.getSociais());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getSenha());    
            ps.setString(8, usuario.getResponsavel1());
            ps.setString(9, usuario.getResponsavel2());
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
            String SQL = "UPDATE usuarios SET nome=?, apelido=?, telefone=?, cpf=?, sociais=?, email=?,"
                    + " senha=? , responsavel1=?, responsavel2=?"
                    + "where id = ?";
            
            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getApelido());
            ps.setString(3, usuario.getTelefone());
            ps.setString(4, usuario.getCpf());
            ps.setString(5, usuario.getSociais());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getSenha());
            ps.setString(8, usuario.getResponsavel1());
            ps.setString(9, usuario.getResponsavel2());
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
            ps = conn.prepareStatement("delete from usuarios where id = ?");
            ps.setString(1, usuario.getEmail());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }  
}
