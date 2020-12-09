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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ufes.republica.model.Reputacao;
import ufes.republica.model.Usuario;

/**
 *
 * @author 55289
 */
public class ReputacaoDAO {

    private Connection conn;

    public ReputacaoDAO() throws Exception {
        try {
            this.conn = Conexao.getConexao();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public void salvar(Usuario usuario) throws Exception {
        PreparedStatement ps = null;

        if (usuario == null) {
            throw new Exception("Reputacao não pode ser nulo!");
        }
        try {
            String SQL = "INSERT INTO reputacao (idUsuario, indice, data"
                    + "values (?, ?, ?);";

            ps = conn.prepareStatement(SQL);

            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            ps.setInt(1, usuario.getId());
            usuario.getReputacao().calculaIndice();
            ps.setDouble(2, usuario.getReputacao().getIndice());
            ps.setString(3, f.format(f.format(usuario.getReputacao().getData())));
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }

    public List<Reputacao> buscarReputacao(Usuario usuario) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = conn.prepareStatement("SELECT * FROM reputacao WHERE reputacao.idUsuario = ?");

        ps.setInt(1, usuario.getId());
        
        List<Reputacao> list = new ArrayList<>();
        while(rs.next()){
            int reputacao = rs.getInt(1);
            int user = rs.getInt(2);
            double indice = rs.getDouble(3);
            String data = rs.getString(4);
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            LocalDate dataFormatada = LocalDate.parse(data);
            
            list.add(new Reputacao(user, indice, dataFormatada, usuario));
        }
        
    }

}
