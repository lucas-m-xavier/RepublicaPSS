/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ufes.republica.model.Usuario;
import ufes.republica.model.UsuarioLogin;

/**
 *
 * @author Lucas
 */
public class UsuarioLoginDAO {
    private Connection conn;
    
    public UsuarioLoginDAO() throws Exception {
        try {
            this.conn = Conexao.getConexao();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public UsuarioLoginDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ArrayList<UsuarioLogin> getAll() throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select * from UsuarioLogin");
            rs = ps.executeQuery();

            ArrayList<UsuarioLogin> list = new ArrayList<UsuarioLogin>();

            while (rs.next()) {
                int idUsuarioLogin = rs.getInt(1);
                int idUsuario = rs.getInt(2);
                String email = rs.getString(3);
                String senha = rs.getString(4);
                
                UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
                Usuario usuario = usuarioDAO.procurarUsuario(idUsuario);

                list.add(new UsuarioLogin(idUsuarioLogin, email, senha, usuario));
            }
            return list;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.fecharConexao(conn, ps, rs);
        }
    }
    
    public void salvar(UsuarioLogin usuarioLogin) throws Exception {
        PreparedStatement ps = null;

        if (usuarioLogin == null) {
            throw new Exception("UsuarioLogin não pode ser nulo!");
        }
        try {
            String SQL = "INSERT INTO usuariologin (email, senha, idUsuario)"
                    + " values (?, ?, (SELECT MAX(idUsuario) FROM Usuario));";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, usuarioLogin.getEmail());
            ps.setString(2, usuarioLogin.getSenha());
            ps.executeUpdate();
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }
    
    public void excluir(UsuarioLogin usuarioLogin) throws Exception {
        PreparedStatement ps = null;
        
        if (usuarioLogin == null) {
            throw new Exception("UsuarioLogin não pode ser nulo!");
        }
        try {            
            ps = conn.prepareStatement("delete from usuarioLogin where idUsuarioLogin = ?");
            ps.setInt(1, usuarioLogin.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }  
}
