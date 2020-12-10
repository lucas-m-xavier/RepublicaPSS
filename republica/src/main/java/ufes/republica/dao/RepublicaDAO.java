/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import ufes.republica.model.Republica;

/**
 *
 * @author Lucas
 */
public class RepublicaDAO {
    
    private Connection conn;

    public RepublicaDAO() throws Exception {
        try {
            this.conn = Conexao.getConexao();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public RepublicaDAO(Connection conn) {
        this.conn = conn;
    }
    
    public void salvar(Republica republica) throws Exception {
        PreparedStatement ps = null;

        if (republica == null) {
            throw new Exception("A república não pode ser nula");
        }
        try {
            String SQL = "INSERT INTO Republica (nome, dataFundacaco, dataExtinsao, vantagens, despesasMedias, vagasTotais, vagasOcupadas, saldoTotal, codEtica, estado"
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            ps = conn.prepareStatement(SQL);
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            ps.setString(1, republica.getNome());
            ps.setString(2, f.format(republica.getFundacao()));
            ps.setString(3, f.format(republica.getExtincao()));
            ps.setString(4, republica.getVantagens());
            ps.setDouble(5, republica.getDespesasMedias());
            ps.setInt(6, republica.getVagasTotais());
            ps.setInt(7, republica.getVagasOcupadas());
            ps.setDouble(8, republica.getSaldoTotal());
            ps.setString(9, republica.getCodEtica());
            ps.setString(10, republica.getEstado().toString());
            
            ps.executeUpdate();
            
            EnderecoDAO enderecoDAO = new EnderecoDAO(conn);
            enderecoDAO.salvar(republica.getEndereco());

        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }
}
