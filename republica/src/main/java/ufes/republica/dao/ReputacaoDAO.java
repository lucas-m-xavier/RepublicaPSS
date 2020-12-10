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
import ufes.republica.model.GeoLocalizacao;
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
    
    public List getAll() throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select * from Reputacao");
            rs = ps.executeQuery();

            List<Reputacao> list = new ArrayList<Reputacao>();

            while (rs.next()) {
                int geoLocalizacao_id = rs.getInt(3);
                String latitude = rs.getString(4);

                list.add(new Reputacao(geoLocalizacao_id, geoLocalizacao_id, LocalDate.MAXusuario));
            }
            return list;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.fecharConexao(conn, ps, rs);
        }
    }
    
    



}
