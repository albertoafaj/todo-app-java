package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Projects;
import util.ConnectionFactory;

public class ProjectsController {
  public void save(Projects project) {
    String sql = "INSERT INTO projects ( name"
        + "description"
        + "dateLastUpdate"
        + "IdOwnerUser) VALUES (?, ?, ?, ?)";
    Connection conn = null;
    PreparedStatement statement = null;

    try {
      conn = ConnectionFactory.getConnection();
      statement = conn.prepareStatement(sql);
      statement.setString(1, project.getName());
      statement.setString(2, project.getDescription());
      statement.setDate(3, new Date(project.getDateLastUpdate().getTime()));
      statement.setInt(4, project.getIdOwnerUser());
      statement.execute();
    } catch (Exception error) {
      throw new RuntimeException("Erro ao tentar salvar o projeto", error);
    } finally {
      ConnectionFactory.closeConnection(conn, statement);
    }
  }

  public List<Projects> getAll(int id) {
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

    return projects;
  }
}
