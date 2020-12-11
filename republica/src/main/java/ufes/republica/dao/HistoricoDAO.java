package ufes.republica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import ufes.republica.model.Historico;
import ufes.republica.model.Usuario;

/**
 *
 * @author Alcebiades
 */
public class HistoricoDAO {
     private Connection conn;
    
    public HistoricoDAO() throws Exception {
        try {
            this.conn = Conexao.getConexao();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }
      public ArrayList<Historico> getHistoricoMorador(Usuario morador) throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select u.* from historico as u inner join historico as h on (? = h.idusuario)");
            ps.setInt(1, morador.getId());
            rs = ps.executeQuery();

            ArrayList<Historico> list = new ArrayList<Historico>();

            Historico historico = new Historico();
            
            while (rs.next()) {
                historico.setId(rs.getInt(1));
                historico.setDataSaida(rs.getDate(2).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                historico.setNomeRepresentante(rs.getString(3));
                historico.setMediaReputacao(rs.getDouble(4));
                historico.setNomeRepublica(rs.getString(5));
            
                list.add(historico);
            }
            
            return list;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.fecharConexao(conn, ps, rs);
        }
    }

    
}
