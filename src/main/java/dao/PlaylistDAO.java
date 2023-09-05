package dao;

import model.*;
import service.AuthorService;
import service.CategoryService;
import service.SingerService;
import service.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO extends ConnectionDatabase {
    UserService userService = new UserService();
    AuthorService authorService = new AuthorService();
    SingerService singerService = new SingerService();
    CategoryService categoryService = new CategoryService();
    static String CREATE_PLAYLIST = "INSERT INTO `playlist` (`name`, `id_user`) VALUES (?, ?);";
    private final String FIND_USER_PLAYLIST = "SELECT * FROM playlist  where playlist.id_user = ?;";
    private final String FIND_PLAYLIST_SONGS = "SELECT *,song.id FROM intermediary_song_playlist as isp LEFT JOIN song ON isp.id_song = song.id where isp.id_playlist = ?;";
    private final String CREATE_ISP = "INSERT INTO intermediary_song_playlist (`id_song`, `id_playlist`) VALUES (?, ?);";
    private final String DELETE_PLAYLIST = "DELETE  FROM playlist WHERE (`id` = ?);";
    private final String DELETE_ISP = "DELETE  FROM intermediary_song_playlist WHERE (`id` = ?);";
    private final String SELECT_PLAYLIST_BY_ID = "SELECT * FROM playlist WHERE id = ?";

    public void createPlaylist(Playlist playlist){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PLAYLIST)) {
            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setInt(2, playlist.getUser().getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Playlist> findUserPlaylists(int id) {
        List<Playlist> playlists = new ArrayList<>();
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_USER_PLAYLIST);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int playlistId = rs.getInt("id");
                String name = rs.getString("name");
                playlists.add(new Playlist(playlistId,name,userService.findById(id)));
            }
            } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return playlists;
    }
    public List<Song> findSongsByPlaylist(int id) {
        List<Song> songs = new ArrayList<>();
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_PLAYLIST_SONGS);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int songId = rs.getInt("id_song");
                String name = rs.getString("name");
                int categoryId = rs.getInt("id_category");
                int authorId = rs.getInt("id_author");
                int singerId = rs.getInt("id_singer");
                String linkImg = rs.getString("link_image");
                String linkSong = rs.getString("link_song");
                songs.add(new Song(songId,name,authorService.findByID(authorId),categoryService.findByID(categoryId),singerService.findByID(singerId),linkImg,linkSong));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return songs;
    }
    public void createISP(ISP isp){
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ISP)) {
            preparedStatement.setInt(1, isp.getSong().getId());
            preparedStatement.setInt(2, isp.getPlaylist().getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteISP(int id){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ISP)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deletePlaylist(int id){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PLAYLIST)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Playlist findById(int id){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_PLAYLIST_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idPlaylist = rs.getInt("id");
                String name = rs.getString("name");
                int iduser = rs.getInt("id_user");
                User user = userService.findById(iduser);
                return new Playlist(idPlaylist,name,user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
