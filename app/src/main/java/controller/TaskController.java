package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Alberto Araújo
 */
public class TaskController {

    public void save(Task task) {
        String sql = "INSERT INTO tasks ( idProject"
                + "name"
                + "description"
                + "notes"
                + "deadline"
                + "dateLastUpdate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setDate(5, new Date(task.getDeadline().getTime()));
            statement.setDate(6, new Date(task.getDateLastUpdate().getTime()));
            statement.execute();
        } catch (SQLException error) {
            throw new RuntimeException("Erro ao salvar a tarefa" + error.getMessage(), error);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET"
                + "idProject = ?"
                + "name = ?"
                + "description = ?"
                + "notes = ?"
                + "deadline = ?"
                + "dateCreated = ?"
                + "dateLastUpdate  = ?"
                + "WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;
        try {
            ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setDate(5, new Date(task.getDeadline().getTime()));
            statement.setDate(6, new Date(task.getDateCreated().getTime()));
            statement.setDate(7, new Date(task.getDateLastUpdate().getTime()));
            statement.execute();
        } catch (Exception error) {
            throw new RuntimeException("Erro ao editar a tarefa" + error.getMessage(), error);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public void removeById(int taskId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        } catch (Exception error) {
            throw new RuntimeException("Erro ao deletar a tarefa", error);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }

    public List<Task> getAll(int idProject) {
        return null;

    };
}
