package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Users;
import util.ConnectionFactory;

public class UserController {
  public void save(Users user) {
    String sql = "INSERT INTO users ( name,"
        + "email,"
        + "password) VALUES (?, ?, ?)";
    Connection conn = null;
    PreparedStatement statement = null;
    try {
      conn = ConnectionFactory.getConnection();
      statement = conn.prepareStatement(sql);
      statement.setString(1, user.getName());
      statement.setString(2, user.getEmail());
      statement.setString(3, user.getPassword());
      statement.execute();
    } catch (Exception error) {
      throw new RuntimeException("Erro ao salvar o usuário" + error.getMessage(), error);
    } finally {
      ConnectionFactory.closeConnection(conn, statement);
    }
  }

  public List<Users> getAll(int idUser) {
    String sql = "SELECT * FROM users WHERE id = ?";
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    List<Users> users = new ArrayList<Users>();
    try {
      conn = ConnectionFactory.getConnection();
      statement = conn.prepareStatement(sql);
      statement.setInt(1, idUser);
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        Users user = new Users();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        users.add(user);
      }
      ;
    } catch (Exception error) {
      throw new RuntimeException("Erro ao buscar o usuário", error);
    } finally {
      ConnectionFactory.closeConnection(conn, statement, resultSet);
    }

    return users;

  };

}
