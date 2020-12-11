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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ufes.republica.model.Reputacao;

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

    public ReputacaoDAO(Connection conn) {
        this.conn = conn;
    }

    public List getAllByCpf(String cpf) throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select * from Reputacao WHERE Reputacao.idUsuario = "
                    + "(SELECT idUsuario FROM Usuario WHERE Usuario.cpf = ?)");
            
            ps.setString(1,cpf);
            rs = ps.executeQuery();

            List<Reputacao> list = new ArrayList<>();

            while (rs.next()) {
                int idreputacao = rs.getInt(1);
                int idusuario = rs.getInt(2);
                LocalDate data = LocalDate.parse(rs.getString(3));
                double indice = rs.getDouble(4);
                //list.add(new Reputacao(geoLocalizacao_id, geoLocalizacao_id, LocalDate.MAXusuario));
                list.add(new Reputacao(idreputacao, indice, data, idusuario));
            }
            return list;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.fecharConexao(conn, ps, rs);
        }
    }

    public void salvar(Reputacao reputacao, String cpf) throws Exception {
        PreparedStatement ps = null;

        if (reputacao == null && cpf == null) {
             throw new Exception("O reputacao ou cpf não pode ser nulo");
        }
        try {
            String SQL = "INSERT INTO Reputacao (idUsuario, indice, data)"
                    + "values ((SELECT idUsuario  FROM Usuario WHERE Usuario.cpf = ?), ?, ?);";

            ps = conn.prepareStatement(SQL);
            ps.setString(1, cpf);
            ps.setDouble(2, reputacao.getIndice());
            ps.setString(3, reputacao.getData().toString());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }

    }

}
