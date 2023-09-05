package dao;

import model.History;
import model.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO extends ConnectionDatabase{
    private final String FIND_SONG_HISTORY ="SELECT count(*) FROM history where id_song = ? ;";
    private final String CREATE_HISTORY = "INSERT INTO history (`id_user`, `id_song`) VALUES (?, ?);";

    private final String FIND_ALL_HISTORY_GROUP_SONG = "SELECT s.id, count(*) AS views_count FROM `history` h JOIN song s ON s.id = h.id_song GROUP BY h.id_song;";
//    private final String DELETE_HISTORY = "DELETE  FROM history WHERE (`id` = ?);";
    public int countSongHistory(int idsong){
        int views = 0;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_SONG_HISTORY)) {
            preparedStatement.setInt(1, idsong);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                views = rs.getInt("count(*)");
            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return views;
    }

    public List<History> findAll(){
        List<History> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_HISTORY_GROUP_SONG)) {
//            preparedStatement.setInt(1, idsong);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                History h = new History();
                Song s = new Song();
                s.setId(rs.getInt(1));
                h.setSong(s);
                h.setView(rs.getInt(2));
                list.add(h);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void createViewHistory(History history){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_HISTORY)) {
            preparedStatement.setInt(1, history.getUser().getId());
            preparedStatement.setInt(2, history.getSong().getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
