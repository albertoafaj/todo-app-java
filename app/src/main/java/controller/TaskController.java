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
        String sql = "INSERT INTO tasks ( idProject"
                + "name"
                + "description"
                + "notes"
                + "deadline"
                + "dateLastUpdate) VALUES (?, ?, ?, ?, ?, ?)";
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
            conn = ConnectionFactory.getConnection();
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
        String sql = "SELECT * FROM task WHERE idProject = ?";
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
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("isCompleted"));
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
