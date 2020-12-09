package ufes.republica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import ufes.republica.business.state.feedback_state.EstadoConcluido;
import ufes.republica.business.state.feedback_state.EstadoEmAberto;
import ufes.republica.business.state.feedback_state.FeedbackState;
import ufes.republica.business.state.tarefa_state.EstadoFinalizada;
import ufes.republica.business.state.tarefa_state.EstadoPendente;
import ufes.republica.business.state.tarefa_state.TarefaState;
import ufes.republica.model.Feedback;
import ufes.republica.model.Tarefa;
import ufes.republica.model.Usuario;

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
                    + "values (?, SELECT MAX(idFeedback) FROM Feedback);";
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
            throw new Exception("Tarefa não pode ser nulo!");
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

            Feedback feedback = new Feedback();
            FeedbackState estado = new EstadoEmAberto(feedback);
            if(rs.getString(5) == "concluido")
                estado = new EstadoConcluido(feedback);

            LocalDate dataCriacao = LocalDate.parse(rs.getDate(2).toString());
            String descricao = rs.getString(3);
            LocalDate dataSolucao = LocalDate.parse(rs.getDate(4).toString());
            boolean EXCLUIDO = rs.getBoolean(5);

            ps = conn.prepareStatement("select idUsuario from usuario INNER JOIN feedbackUsuario where idFeedback = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id );
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            Usuario usuario = usuarioDAO.procurarUsuario(rs.getInt(1));

            return new Feedback(id, dataCriacao, descricao, dataSolucao, usuario, EXCLUIDO, estado);

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            rs.close();
            ps.close();
        }
    }


}
