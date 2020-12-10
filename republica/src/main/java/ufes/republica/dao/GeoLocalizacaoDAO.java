package ufes.republica.dao;

import ufes.republica.model.GeoLocalizacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ufes.republica.model.Endereco;

public class GeoLocalizacaoDAO {

    private Connection conn;

    public GeoLocalizacaoDAO() throws Exception {
        try {
            this.conn = Conexao.getConexao();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public GeoLocalizacaoDAO(Connection conn) {
        this.conn = conn;
    }

    public GeoLocalizacao procurarGeoLocalizacao(int id) throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from GeoLocalizacao where idGeoLocalizacao = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id );
            }

            String latitude = rs.getString(2);
            String longitude = rs.getString(3);

            return new GeoLocalizacao(id,latitude, longitude);

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            rs.close();
            ps.close();
        }
    }

    public List getAll() throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select * from GeoLocalizacao");
            rs = ps.executeQuery();

            List<GeoLocalizacao> list = new ArrayList<GeoLocalizacao>();

            while (rs.next()) {
                int geoLocalizacao_id = rs.getInt(1);
                String latitude = rs.getString(2);
                String longitude = rs.getString(3);

                list.add(new GeoLocalizacao(latitude, longitude));
            }
            return list;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.fecharConexao(conn, ps, rs);
        }
    }

    public void salvar(GeoLocalizacao geoLocalizacao) throws Exception {
        PreparedStatement ps = null;

        if (geoLocalizacao == null) {
            throw new Exception("A GeoLocalizacao não pode ser nula!");
        }
        try {
            String SQL = "INSERT INTO Geolocalizacao (latitude, longitude)"
                    + " values (?, ?);";
            ps = conn.prepareStatement(SQL);
            ps.setString(1, geoLocalizacao.getLatitude());
            ps.setString(2, geoLocalizacao.getLongitude());
            System.out.println(SQL);
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
