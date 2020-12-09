package ufes.republica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import ufes.republica.model.Feedback;

public class FeedbackDAO {
    private Connection conn;

    public FeedbackDAO() throws Exception {
        try {
            this.conn = Conexao.getConexao();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }
    
    public void salvar(Feedback feedback) throws Exception {
        PreparedStatement ps = null;

        if (feedback == null) {
            throw new Exception("O feedback não pode ser nulo!");
        }
        try {
            String SQL = "INSERT INTO Feedback (dataCriacao, descricao, dataSolucao)"
                    + "values (?, ?, ?);";

            ps = conn.prepareStatement(SQL);

            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            ps.setString(1, f.format(feedback.getDataCriacao()));
            ps.setString(2, feedback.getDescricao());
            ps.setString(3, f.format(feedback.getDataSolucao()));
            ps.executeUpdate();

            SQL = "INSERT INTO FeedbackUsuario (idUsuario, idFeedback)"
                    + "values (?, SELECT LAST_INSERT_ID FROM Feedback);";
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, feedback.getUsuario().getId());
            
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }
    
    public void atualizar(Feedback feedback) throws Exception {
        PreparedStatement ps = null;

        if (feedback == null) {
            throw new Exception("O feedback não pode ser nulo!");
        }
        try {
            String SQL = "UPDATE feedback SET dataCriacao=?, descricao=?, dataSolucao=?"
                    + "where id = ?";

            ps = conn.prepareStatement(SQL);
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            ps.setString(1, f.format(feedback.getDataCriacao()));
            ps.setString(2, feedback.getDescricao());
            ps.setString(3, f.format(feedback.getDataSolucao()));
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }
    
       public void excluir(Feedback feedback) throws Exception {
        PreparedStatement ps = null;

        if (feedback == null) {
            throw new Exception("o feedback não pode ser nulo!");
        }
        try {
            ps = conn.prepareStatement("delete from feedback where idFeedback = ?");
            ps.setInt(1, feedback.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }
       
    public Feedback procurarFeedback(int id) throws Exception {
        PreparedStatement ps = null;
        
        ResultSet rs = null;
        try {            
            ps = conn.prepareStatement("select * from Feedback where idFeedback = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id );
            }
            
            String dataCriacao = rs.getString(2);
            String dataSolucao = rs.getString(3);
            String descricao = rs.getString(4);
            
            
            return new Feedback(id, dataCriacao, descricao, dataSolucao);
            
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            rs.close();
            ps.close();            
        }
    }
    

}
