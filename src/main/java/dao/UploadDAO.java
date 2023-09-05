package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class UploadDAO {
    private String url = "jdbc:mysql://localhost:3306/nhp_mp3_db";
    private String username = "root";
    private String password = "050401";
    private final String INSERT_SONG = "INSERT INTO music (name, link_song) VALUES (?, ?)";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            java.lang.Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void saveMusic(String musicName, String filePath) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(INSERT_SONG);
            statement.setString(1, musicName);
            statement.setString(2, filePath);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
