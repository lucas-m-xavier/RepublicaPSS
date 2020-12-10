package ufes.republica.dao;

import ufes.republica.enums.EnumUF;
import ufes.republica.model.Endereco;
import ufes.republica.model.GeoLocalizacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ufes.republica.model.Republica;

public class EnderecoDAO {

    private Connection conn;

    public EnderecoDAO() throws Exception {
        try {
            this.conn = Conexao.getConexao();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public EnderecoDAO(Connection conn) {
        this.conn = conn;
    }

    public Endereco procurarEndereco(int id) throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from Endereco where idEndereco = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id );
            }

            GeoLocalizacaoDAO geoLocalizacaoDAO = new GeoLocalizacaoDAO(conn);
            GeoLocalizacao geoLocalizacao = geoLocalizacaoDAO.procurarGeoLocalizacao(rs.getInt(2));

            String cep = rs.getString(3);
            String bairro = rs.getString(4);
            String referencia = rs.getString(5);
            int numero = rs.getInt(6);
            String logradouro = rs.getString(7);

            EnumUF uf = null;
            uf.setEstado(rs.getString(8));

            return new Endereco(id, cep, bairro, referencia, numero, logradouro, uf, geoLocalizacao);

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            rs.close();
            ps.close();
        }
    }

    public void salvar(Endereco endereco) throws Exception {
        PreparedStatement ps = null;

        if (endereco == null) {
            throw new Exception("O endereço não pode ser nulo");
        }
        try {
            String SQL = "INSERT INTO Endereco (cEP, bairro, logradouro, numero, referencia, uf"
                    + "values (?, ?, ?, ?, ?, ?);";

            ps = conn.prepareStatement(SQL);
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getBairro());
            ps.setString(3, endereco.getLogradouro());
            ps.setInt(4, endereco.getNumero());
            ps.setString(5, endereco.getReferencia());
            ps.setString(6, endereco.getUf().getValor());
            
            ps.executeUpdate();
            
            GeoLocalizacaoDAO geoLocalizacaoDAO = new GeoLocalizacaoDAO(conn);
            geoLocalizacaoDAO.salvar(endereco.getGeoLocalizacao());

        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }

    public void atualizar(GeoLocalizacao geoLocalizacao) throws Exception {
        PreparedStatement ps = null;

        if (geoLocalizacao == null) {
            throw new Exception("GeoLocalizacao não pode ser nulo!");
        }
        try {
            String SQL = "UPDATE geoLocalizacao SET latitude=?, longitude=?"
                    + "where id = ?";

            ps = conn.prepareStatement(SQL);
            ps.setString(1, geoLocalizacao.getLatitude());
            ps.setString(2, geoLocalizacao.getLongitude());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }

    public void excluir(GeoLocalizacao geoLocalizacao) throws Exception {
        PreparedStatement ps = null;

        if (geoLocalizacao == null) {
            throw new Exception("GeoLocalizacao não pode ser nulo!");
        }
        try {
            ps = conn.prepareStatement("delete from geoLocalizacao where idGeoLocalizacao = ?");
            ps.setInt(1, geoLocalizacao.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }

}
