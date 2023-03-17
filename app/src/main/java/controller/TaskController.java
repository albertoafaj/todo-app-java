package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Alberto Araújo
 */
public class TaskController {

    public void save(Task task) {
        String sql = "INSERT INTO tasks ( idProject,"
                + "name,"
                + "description,"
                + "notes,"
                + "deadline) VALUES (?, ?, ?, ?, ?)";
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
            statement.execute();
        } catch (SQLException error) {
            throw new RuntimeException("Erro ao salvar a tarefa" + error.getMessage(), error);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }

    public void update(Task task) {
        String sql = "UPDATE tasks "
                + "SET idProject = ?,"
                + "name = ?,"
                + "description = ?,"
                + "notes = ?,"
                + "deadline = ?,"
                + "dateLastUpdate  = ?,"
                + "completed  = ? "
                + "WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;
        try {
            java.util.Date today = new java.util.Date();
            java.sql.Date getCurrentDatetime = new java.sql.Date(today.getTime());
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setDate(5, new Date(task.getDeadline().getTime()));
            statement.setDate(6, getCurrentDatetime);
            statement.setBoolean(7, task.getCompleted());
            statement.setInt(8, task.getId());
            statement.execute();
        } catch (Exception error) {
            throw new RuntimeException("Erro ao editar a tarefa" + error.getMessage(), error);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public void removeById(int taskId) {
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
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<Task>();
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setDateCreated(resultSet.getDate("dateCreated"));
                task.setDateLastUpdate(resultSet.getDate("dateLastUpdate"));
                tasks.add(task);
            }
            ;
        } catch (Exception error) {
            throw new RuntimeException("Erro ao buscar a tarefa", error);
        } finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);
        }

        return tasks;

    };

    public List<Task> getById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<Task>();
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setDateCreated(resultSet.getDate("dateCreated"));
                task.setDateLastUpdate(resultSet.getDate("dateLastUpdate"));
                tasks.add(task);
            }
            ;
        } catch (Exception error) {
            throw new RuntimeException("Erro ao buscar a tarefa", error);
        } finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);
        }

        return tasks;

    };
}
