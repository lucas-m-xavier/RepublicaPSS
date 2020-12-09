package ufes.republica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            String SQL = "INSERT INTO Feedback (dataCriacao, descricao, dataSolucao"
                    + "values (?, ?, ?);";

            ps = conn.prepareStatement(SQL);

            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            ps.setString(1, f.format(feedback.getDataCriacao()));
            ps.setString(2, feedback.getDescricao());
            ps.setString(3, f.format(feedback.getDataSolucao()));
            ps.executeUpdate();

            SQL = "INSERT INTO TarefaUsuario (idUsuario, idTarefa"
                    + "values (?, SELECT LAST_INSERT_ID FROM tarefa);";
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, feedback.getUsuario().getId());
            
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }
    
    
}
