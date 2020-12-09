package ufes.republica.dao;

import ufes.republica.business.state.tarefa_state.EstadoFinalizada;
import ufes.republica.business.state.tarefa_state.EstadoPendente;
import ufes.republica.business.state.tarefa_state.TarefaState;
import ufes.republica.model.GeoLocalizacao;
import ufes.republica.model.Tarefa;
import ufes.republica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private Connection conn;

    public TarefaDAO() throws Exception {
        try {
            this.conn = Conexao.getConexao();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public Tarefa procurarTarefa(int id) throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from Tarefa where idTarefa = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id );
            }

            Tarefa tarefa = new Tarefa();
            TarefaState estado = new EstadoPendente(tarefa);
            if(rs.getString(2) == "finalizada")
                estado = new EstadoFinalizada(tarefa);

            String descricao = rs.getString(3);
            LocalDate dataAgendamento = LocalDate.parse(rs.getDate(4).toString());
            LocalDate dataTermino = LocalDate.parse(rs.getDate(5).toString());

            ps = conn.prepareStatement("select idUsuario from usuario INNER JOIN usuarioTarefa where idTarefa = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id );
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            Usuario usuario = usuarioDAO.procurarUsuario(rs.getInt(2));

            return new Tarefa(id, dataAgendamento, descricao, dataTermino, usuario, estado);

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
                    + "values (?, SELECT MAX(idTarefa) FROM tarefa);";
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
