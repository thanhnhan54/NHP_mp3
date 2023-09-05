package dao;

import model.Author;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends  ConnectionDatabase{
    private final String SELECT_USER = "SELECT * FROM category;";
    private final String SELECT_USER_BY_ID = "SELECT * FROM category where id = ?;";
    private final String CREATE_USER = "INSERT INTO `category` (`name`) VALUES (?);";
    private final String EDIT_USER = "UPDATE `category` SET `name` = ? WHERE (`id` = ?)";
    private final String DELETE_USER = "DELETE FROM `category` WHERE (`id` = ?)";

    public List<Category> findAll(){
        List<Category> categorys = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_USER)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categorys.add(new Category(id, name));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return categorys;
    }
    public Category findById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_USER_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idCus = rs.getInt("id");
                String name = rs.getString("name");
                return new Category(idCus, name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void createCategory(Category category) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, category.getName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void editAuthor(Category category){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2,category.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteAuthor(int id) {

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
