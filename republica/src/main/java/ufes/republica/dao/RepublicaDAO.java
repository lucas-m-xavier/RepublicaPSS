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
            String SQL = "INSERT INTO Republica (nome, dataFundacao, vantagens, despesasMedias, "
                    + "vagasTotais, vagasOcupadas, saldoTotal, codEtica, idEndereco)"
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, (SELECT MAX(idEndereco) FROM Endereco));";

            ps = conn.prepareStatement(SQL);
            ps.setString(1, republica.getNome());
            ps.setString(2, republica.getFundacao().toString());
            ps.setString(3, republica.getVantagens());
            ps.setDouble(4, republica.getDespesasMedias());
            ps.setInt(5, republica.getVagasTotais());
            ps.setInt(6, republica.getVagasOcupadas());
            ps.setDouble(7, republica.getSaldoTotal());
            ps.setString(8, republica.getCodEtica());
            
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
