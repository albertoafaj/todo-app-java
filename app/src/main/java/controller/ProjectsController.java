package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Projects;
import util.ConnectionFactory;

public class ProjectsController {
  public void save(Projects project) {
    String sql = "INSERT INTO projects ( name,"
        + "description,"
        + "IdOwnerUser) VALUES (?, ?, ?)";
    Connection conn = null;
    PreparedStatement statement = null;

    try {
      conn = ConnectionFactory.getConnection();
      statement = conn.prepareStatement(sql);
      statement.setString(1, project.getName());
      statement.setString(2, project.getDescription());
      statement.setInt(3, project.getIdOwnerUser());
      statement.execute();
    } catch (Exception error) {
      throw new RuntimeException("Erro ao tentar salvar o projeto", error);
    } finally {
      ConnectionFactory.closeConnection(conn, statement);
    }
  }

  public List<Projects> getAll() {
    String sql = "SELECT * FROM projects";
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    List<Projects> projects = new ArrayList<Projects>();
    try {
      conn = ConnectionFactory.getConnection();
      statement = conn.prepareStatement(sql);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Projects project = new Projects();
        project.setId(resultSet.getInt("id"));
        project.setName(resultSet.getString("name"));
        project.setDescription(resultSet.getString("description"));
        project.setDateCreated(resultSet.getDate("dateCreated"));
        project.setDateLastUpdate(resultSet.getDate("dateLastUpdate"));
        project.setIdOwnerUser(resultSet.getInt("IdOwnerUser"));
        projects.add(project);
      }

    } catch (Exception error) {
      throw new RuntimeException("Erro ao buscar o projeto", error);
    } finally {
      ConnectionFactory.closeConnection(conn, statement, resultSet);
    }
    ;

    return projects;
  }

  public void update(Projects project) {
    Connection conn = null;
    PreparedStatement statement = null;
    String sql = "UPDATE projects SET name = ?, description = ?, dateLastUpdate  = ? WHERE id = ?";

    try {
      java.util.Date today = new java.util.Date();
      java.sql.Date getCurrentDatetime = new java.sql.Date(today.getTime());
      conn = ConnectionFactory.getConnection();
      statement = conn.prepareStatement(sql);
      statement.setString(1, project.getName());
      statement.setString(2, project.getDescription());
      statement.setDate(3, getCurrentDatetime);
      statement.setInt(4, project.getId());
      statement.execute();

    } catch (Exception error) {
      throw new RuntimeException("Erro ao atualizar o projeto", error);
    } finally {
      ConnectionFactory.closeConnection(conn, statement);
    }
  };

  public void removeById(int projectId) {
    Connection conn = null;
    PreparedStatement statement = null;
    String sql = "DELETE FROM projects WHERE id = ?";
    try {
      conn = ConnectionFactory.getConnection();
      statement = conn.prepareStatement(sql);
      statement.setInt(1, projectId);
      statement.execute();
    } catch (Exception error) {
      throw new RuntimeException("Erro ao deletar o projeto", error);
    } finally {
      ConnectionFactory.closeConnection(conn, statement);
    }
  };
}
