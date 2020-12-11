package ufes.republica.dao;

import ufes.republica.model.Tarefa;
import ufes.republica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

            String descricao = rs.getString(3);
            LocalDate dataAgendamento = rs.getDate(4).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataTermino = rs.getDate(5).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            boolean finalizada = rs.getBoolean(6);

            ps = conn.prepareStatement("select idUsuario from usuario INNER JOIN tarefaUsuario where idTarefa = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + id );
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            Usuario usuario = usuarioDAO.procurarUsuario(rs.getInt(2));

            return new Tarefa(id, dataAgendamento, descricao, dataTermino, finalizada, usuario);

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            rs.close();
            ps.close();
        }
    }

    public ArrayList getAll() throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select * from tarefa");
            rs = ps.executeQuery();

            ArrayList<Tarefa> tarefas = new ArrayList<>();

            while (rs.next()) {
                int tarefa_id = rs.getInt(1);
                String descricao = rs.getString(2);
                String data = rs.getString(3);
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dataAgendamento = LocalDate.parse(data,formatter);
                 
                data = rs.getString(4);
                
                LocalDate dataTermino = LocalDate.parse(data,formatter);
                
                boolean finalizada = rs.getBoolean(5);
                
                tarefas.add(new Tarefa(tarefa_id,dataAgendamento,descricao,dataTermino,finalizada));
            }
            return tarefas;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.fecharConexao(conn, ps, rs);
        }
    }

public String getNomeResponsavel(Tarefa tarefa) throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select usuario.nome from tarefa inner join tarefausuario on (tarefa.idtarefa = tarefausuario.idtarefa)" +
" inner join usuario on(tarefausuario.idusuario = usuario.idusuario) where tarefa.idtarefa = ?;");
            ps.setInt(1, tarefa.getId());
            rs = ps.executeQuery();

            String nome="";
            
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
