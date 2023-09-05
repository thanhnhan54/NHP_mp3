package dao;

import model.Like;
import model.Song;
import model.User;
import service.SongService;
import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikeDAO extends ConnectionDatabase{
    UserService userService = new UserService();
    SongService songService = new SongService();
    private final String COUNT_SONG_LIKE = "SELECT COUNT(*) FROM `like` WHERE id_song = ?";
    private final String CREATE_LIKE = "INSERT INTO `like` (`id_user`, `id_song`, `status`) VALUES (?, ?, ?);";
    private final String CHECK_LIKE = "UPDATE `like` SET `status` = ? WHERE (`id_user` = ? and id_song = ?);";
//    private final String CHECK_LIKED = "SELECT * FROM nhp_mp3.like where id_user = ? and id_song = ?;";
    private final String FIND_LIKE = "SELECT * FROM `like` where id_user = ? and id_song = ?;";
    private final String SELECT_LIKE_BY_USERID = "SELECT * FROM `like` WHERE id_user = ?";

    public int countSongLike(int idsong){
        int likes = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SONG_LIKE)) {
            preparedStatement.setInt(1, idsong);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                likes = rs.getInt("COUNT(*)");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return likes;
    }
    public void createLike(Like like){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LIKE)) {
            preparedStatement.setInt(1, like.getUser().getId());
            preparedStatement.setInt(2, like.getSong().getId());
            preparedStatement.setInt(3, 1);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void unlike(Like like){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LIKE)) {
            preparedStatement.setInt(1,0);
            preparedStatement.setInt(2, like.getUser().getId());
            preparedStatement.setInt(3, like.getSong().getId());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void like(Like like){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LIKE)) {
            preparedStatement.setInt(1,1);
            preparedStatement.setInt(2, like.getUser().getId());
            preparedStatement.setInt(3, like.getSong().getId());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Like findLike(int idUser, int idSong){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_LIKE)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2,idSong);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int iduser = rs.getInt("id_user");
                int idsong = rs.getInt("id_song");
                int status = rs.getInt("status");
                return new Like(id,userService.findById(idUser),songService.findByID(idSong),status);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Like> findUserLike(int iduser){
        List<Like> likes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIKE_BY_USERID)) {
            preparedStatement.setInt(1, iduser);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int idlike = rs.getInt("id");
                int id_user = rs.getInt("id_user");
                int id_song = rs.getInt("id_song");
                int status = rs.getInt("status");
                User user = userService.findById(id_user);
                Song song = songService.findByID(id_song);
                Like like = new Like(idlike,user,song,status);
                likes.add(like);
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        return likes;
    }

}
