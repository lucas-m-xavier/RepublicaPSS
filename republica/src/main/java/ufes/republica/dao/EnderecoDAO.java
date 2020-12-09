package ufes.republica.dao;

import ufes.republica.enums.EnumUF;
import ufes.republica.model.Endereco;
import ufes.republica.model.GeoLocalizacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            String cep = rs.getString(2);
            String bairro = rs.getString(3);
            String referencia = rs.getString(4);
            int numero = rs.getInt(5);
            String logradouro = rs.getString(6);
            EnumUF uf = null;
            uf.setValor(rs.getInt(7));
            GeoLocalizacaoDAO geoLocalizacaoDAO = new GeoLocalizacaoDAO(conn);
            GeoLocalizacao geoLocalizacao = geoLocalizacaoDAO.procurarGeoLocalizacao(id);

            return new Endereco(id, cep, bairro, referencia, numero, logradouro, uf, geoLocalizacao);

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            rs.close();
            ps.close();
        }
    }

    public void salvar(GeoLocalizacao geolocalizacao) throws Exception {
        PreparedStatement ps = null;

        if (geolocalizacao == null) {
            throw new Exception("A geolocalizacao não pode ser nula!");
        }
        try {
            String SQL = "INSERT INTO Geolocalizacao (latitude, longitude"
                    + "values (?, ?);";

            ps = conn.prepareStatement(SQL);
            ps.setString(1, geolocalizacao.getLatitude());
            ps.setString(2, geolocalizacao.getLongitude());
            ps.executeUpdate();

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
