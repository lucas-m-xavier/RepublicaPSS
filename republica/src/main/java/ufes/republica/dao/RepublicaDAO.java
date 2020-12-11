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
import java.time.ZoneId;
import ufes.republica.business.state.republica_state.EstadoAberta;
import ufes.republica.business.state.republica_state.EstadoExtinta;
import ufes.republica.business.state.republica_state.EstadoLotada;
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

    public Republica getRepublicaByCPF(String cpf) throws SQLException, Exception {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("select republica.* from Republica inner join historico " +
                                                        "on (republica.idRepublica = historico.idRepublica) inner join Usuario " +
                                                        "on(historico.idUsuario = usuario.idUsuario) " +
                                                        "where usuario.cpf = ? and historico.dataSaida is null;");
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o CPF: " + cpf);
            }

            int idRepublica = rs.getInt(1);
            String republicaNome = rs.getString(2);
            LocalDate dataCriacao = rs.getDate(3).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataExtinsao = rs.getDate(4).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String vantagens = rs.getString(5);
            double despesasMedias = rs.getDouble(6);
            int vagasTotais = rs.getInt(7);
            int vagasOcupadas = rs.getInt(8);
            double saldoTotal = rs.getDouble(9);
            String codEtica = rs.getString(10);
            String repEstado = rs.getString(11);

            Republica republica = new Republica();
            republica.setId(idRepublica);
            republica.setNome(republicaNome);
            republica.setFundacao(dataCriacao);
            republica.setExtincao(dataExtinsao);
            republica.setVantagens(vantagens);
            republica.setDespesasMedias(despesasMedias);
            republica.setVagasTotais(vagasTotais);
            republica.setVagasOcupadas(vagasOcupadas);
            republica.setSaldoTotal(saldoTotal);
            republica.setCodEtica(codEtica);

            if (repEstado.toLowerCase().equalsIgnoreCase("lotada")) {
                republica.setEstado(new EstadoLotada(republica));

            } else if (repEstado.toLowerCase().equalsIgnoreCase("extinta")) {
                republica.setEstado(new EstadoExtinta(republica));
            }
            republica.setEstado(new EstadoAberta(republica));

            return republica;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            rs.close();
            ps.close();
        }
    }
}
