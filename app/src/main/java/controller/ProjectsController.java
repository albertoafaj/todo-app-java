package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
}
