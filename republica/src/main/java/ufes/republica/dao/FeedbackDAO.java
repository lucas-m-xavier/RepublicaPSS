package ufes.republica.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import ufes.republica.model.Feedback;
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

    public void salvar(Feedback feedback, String cpf) throws Exception {
        PreparedStatement ps = null;

        if (feedback == null) {
            throw new Exception("O feedback não pode ser nulo!");
        }
        try {
            String SQL = "INSERT INTO Feedback (idUsuario,dataCriacao, descricao, dataSolucao)"
                    + "values (select idUsuario from Usuario where usuario.cpf = ?,?, ?, ?);";

            ps = conn.prepareStatement(SQL);

            ps.setString(1, cpf);
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
            ps.setInt(4, feedback.getId());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }

    public void excluir(int id) throws Exception {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("delete from feedback where idFeedback = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }

    public ArrayList<Feedback> procurarFeedbackByRepublica(int idRepublica) throws Exception {
        PreparedStatement ps = null;
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from Feedback where idRepublica= ?");
            ps.setInt(1, idRepublica);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + idRepublica);
            }
            while (rs.next()) {
                int idFeedback = rs.getInt(1);
                LocalDate dataCriacao = rs.getDate(2).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String descricao = rs.getString(3);
                LocalDate dataSolucao = rs.getDate(4).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                boolean EXCLUIDO = rs.getBoolean(5);
                boolean concluida = rs.getBoolean(6);

                ps = conn.prepareStatement("select idUsuario from usuario INNER JOIN feedbackUsuario where idFeedback = ?");
                ps.setInt(1, idRepublica);
                rs = ps.executeQuery();

                ps = conn.prepareStatement("select idUsuario from historico "
                        + "innerjoin republica on (historico.idRepublica = ?) innerjoin usuario "
                        + "on (usuario.idUsuario = historico.idusuario) where historico.dataSaida is null");
                ps.setInt(1, idRepublica);

                UsuarioDAO daousuario = new UsuarioDAO(conn);
                Usuario usuario = daousuario.procurarUsuario(rs.getInt(1));
                feedbacks.add(new Feedback(idRepublica, idFeedback, dataCriacao, descricao, dataSolucao, EXCLUIDO, concluida, usuario));

            }
            return feedbacks;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            rs.close();
            ps.close();
        }
    }

    public String getNomeResponsavel(Feedback feedback) throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select usuario.nome from feedback inner join feedbackausuario on (feedback.idfeedback = feedback.usuario.idfeedback)"
                    + " inner join usuario on(feedbackusuario.idusuario = usuario.idusuario) where feedback.idfeedback = ?;");
            ps.setInt(1, feedback.getId());
            rs = ps.executeQuery();

            String nome = "";

            while (rs.next()) {
                nome += rs.getString(1);
            }
            return nome;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.fecharConexao(conn, ps, rs);
        }
    }

    public void confirmar(int id) throws Exception {
        PreparedStatement ps = null;
        try {
            String SQL = "UPDATE feedback SET concluida = ? where idFeedback = ?";

            ps = conn.prepareStatement(SQL);
            ps.setBoolean(1, true);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }

    /*public Feedback procurarFeedbackByID(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Feedback p = null;
            try {
            ps = conn.prepareStatement("select * from Feedback where idFeedback= ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + idRepublica);
            }

            int idFeedback = rs.getInt(1);
            LocalDate dataCriacao = rs.getDate(2).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String descricao = rs.getString(3);
            LocalDate dataSolucao = rs.getDate(4).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            boolean EXCLUIDO = rs.getBoolean(5);
            boolean concluida = rs.getBoolean(6);

            Feedback p = new Feedback(idFeedback, id, dataCriacao, descricao, dataSolucao, EXCLUIDO, concluida, null);

            }
        return p;
        catch (SQLException sqle) {
            throw new Exception(sqle);
        }finally {
            rs.close();
            ps.close();
        }
    }
*/
}
