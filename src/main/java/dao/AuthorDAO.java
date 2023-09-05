package dao;

import model.Author;
import model.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends  ConnectionDatabase{
    private final String SELECT_USER = "SELECT * FROM author;";
    private final String SELECT_USER_BY_ID = "SELECT * FROM author where id = ?;";
    private final String CREATE_AUTHOR = "INSERT INTO `author` (`name`) VALUES (?);";
    private final String EDIT_AUTHOR = "UPDATE `author` SET `name` = ? WHERE (`id` = ?);";
    private final String DELETE_AUTHOR = "DELETE FROM `author` WHERE (`id` = ?);";

    public List<Author> findAll(){
        List<Author> authors = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_USER)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                authors.add(new Author(id, name));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return authors;
    }
    public Author findById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_USER_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idAuthor = rs.getInt("id");
                String name = rs.getString("name");
                return new Author(idAuthor, name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void createAuthor(Author author) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_AUTHOR)) {
            preparedStatement.setString(1, author.getName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void editAuthor(Author author){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_AUTHOR)) {
            preparedStatement.setString(1, author.getName());
            preparedStatement.setInt(2,author.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteAuthor(int id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHOR)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
