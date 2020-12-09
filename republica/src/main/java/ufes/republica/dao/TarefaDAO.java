package ufes.republica.dao;

import ufes.republica.model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class TarefaDAO {

    private Connection conn;

    public TarefaDAO() throws Exception {
        try {
            this.conn = Conexao.getConexao();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public void salvar(Tarefa tarefa) throws Exception {
        PreparedStatement ps = null;

        if (tarefa == null) {
            throw new Exception("Tarefa não pode ser nulo!");
        }
        try {
            String SQL = "INSERT INTO tarefa (dataAgendamento, descricao, dataTermino)"
                    + "values (?, ?, ?);";

            ps = conn.prepareStatement(SQL);

            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            ps.setString(1, f.format(tarefa.getDataAgendamento()));
            ps.setString(2, tarefa.getDescricao());
            ps.setString(3, f.format(tarefa.getDataTermino()));
            ps.executeUpdate();

            SQL = "INSERT INTO TarefaUsuario (idUsuario, idTarefa)"
                    + "values (?, SELECT LAST_INSERT_ID FROM tarefa);";
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, tarefa.getUsuario().getId());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }

    public void atualizar(Tarefa tarefa) throws Exception {
        PreparedStatement ps = null;

        if (tarefa == null) {
            throw new Exception("Tarefa não pode ser nulo!");
        }
        try {
            String SQL = "UPDATE tarefa SET dataAgendamento=?, descricao=?, dataTermino=?"
                    + "where id = ?";

            ps = conn.prepareStatement(SQL);
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            ps.setString(1, f.format(tarefa.getDataAgendamento()));
            ps.setString(2, tarefa.getDescricao());
            ps.setString(3, f.format(tarefa.getDataTermino()));
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }

    public void excluir(Tarefa tarefa) throws Exception {
        PreparedStatement ps = null;

        if (tarefa == null) {
            throw new Exception("Tarefa não pode ser nulo!");
        }
        try {
            ps = conn.prepareStatement("delete from tarefa where idTarefa = ?");
            ps.setInt(1, tarefa.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);
        } finally {
            Conexao.fecharConexao(conn, ps);
        }
    }

}
