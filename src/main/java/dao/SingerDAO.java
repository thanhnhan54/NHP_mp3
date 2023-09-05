package dao;

import model.Singer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingerDAO extends ConnectionDatabase{
    private final String SELECT_USER = "SELECT * FROM singer;";
    private final String SELECT_USER_BY_ID = "SELECT * FROM singer where id = ?;";
    private final String CREATE_USER = "INSERT INTO `singer` (`name`) VALUES (?)";
    private final String EDIT_USER = "UPDATE `singer` SET `name` = ? WHERE (`id` = ?)";
    private final String DELETE_USER = "DELETE FROM `singer` WHERE (`id` = ?)";

    public List<Singer> findAll(){
        List<Singer> singers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_USER)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                singers.add(new Singer(id, name));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return singers;
    }
    public Singer findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_USER_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idSinger = rs.getInt("id");
                String name = rs.getString("name");
                return new Singer(idSinger, name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void createSinger(Singer singer){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, singer.getName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void editSinger(Singer singer){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER)) {
            preparedStatement.setString(1, singer.getName());
            preparedStatement.setInt(2,singer.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteSinger(int id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
