package database;

import java.sql.*;

public class DatabaseConnector {

  private static Connection con;

  static {
    try {
      con = DriverManager.getConnection("jdbc:postgresql://200.134.10.32/a",
          "1801JuLuPe", "728597");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static void registerImage(String username, String path, int iterations, Timestamp start, Timestamp finish) {
    String query = "INSERT INTO images(username, path, iterations, start, finish) VALUES(?,?,?,?,?)";
    
    try {
      PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, username);
      stmt.setString(2, path);
      stmt.setInt(3, iterations);
      stmt.setTimestamp(4, start);
      stmt.setTimestamp(5, finish);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static String getImagesForUsername(String username) {
    ResultSet rs = null;
    String sql = "SELECT id, iterations, start, finish FROM images WHERE username=?";
    String list = "";

    try (PreparedStatement stmt = con.prepareStatement(sql)) {
      stmt.setString(1, username);
      rs = stmt.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        int iterations = rs.getInt("iterations");
        Timestamp start = rs.getTimestamp("start");
        Timestamp finish = rs.getTimestamp("finish");
        list += id + "," + iterations + "," + start + "," + finish + "#";
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return list;
  }
  
  public static String getPathForId(int id) {
    ResultSet rs = null;
    String sql = "SELECT path FROM images WHERE id=?";
    String path = "";

    try (PreparedStatement stmt = con.prepareStatement(sql)) {
      stmt.setInt(1, id);
      rs = stmt.executeQuery();
      rs.next();
      path = rs.getString("path");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return path;
  }
  
}

