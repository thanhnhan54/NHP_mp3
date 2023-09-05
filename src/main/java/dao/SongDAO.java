package dao;

import dto.Pageable;
import model.Author;
import model.Category;
import model.Singer;
import model.Song;
import service.AuthorService;
import service.CategoryService;
import service.SingerService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SongDAO extends ConnectionDatabase {
    AuthorService authorService = new AuthorService();
    SingerService singerService = new SingerService();
    CategoryService categoryService = new CategoryService();
    private String SELECT_ALL_SONG_WITH_FILTER = "SELECT s.*, a.`name` as name_author , c.`name` as type, sg.`name` as name_singer  FROM song s \n" +
            "LEFT JOIN author a ON s.id_author = a.id\n" +
            "LEFT JOIN category c ON s.id_category = c.id\n" +
            "LEFT JOIN singer sg ON s.id_singer = sg.id\n";
    private final String SELECT_USER_BY_ID = "SELECT song.*, author.`name` as name_author , category.`name` as type, singer.`name` as name_singer  FROM song  \n" +
            "LEFT JOIN author  ON song.id_author = author.id\n" +
            "LEFT JOIN category  ON song.id_category = category.id\n" +
            "LEFT JOIN singer  ON song.id_singer = singer.id where song.id = ?;";
    private final String CREATE_USER = "INSERT INTO `song` (`name`, `id_author`, `id_category`, `id_singer`, `link_song`, `link_image`) VALUES (?, ?,?, ?, ?, ?);";
    private final String EDIT_USER = "UPDATE `song` SET `name` = ?, `id_author` = ?, `id_category` = ?, `id_singer` = ?, `link_song` = ?, `link_image` = ? WHERE (`id` = ?);";
    private final String DELETE_USER = "DELETE FROM `song` WHERE (`id` = ?);";
    private final String SELECT_ALL_SONG = "SELECT song.*, author.`name` as name_author , category.`name` as type, singer.`name` as name_singer  FROM song  \n" +
            "LEFT JOIN author  ON song.id_author = author.id\n" +
            "LEFT JOIN category  ON song.id_category = category.id\n" +
            "LEFT JOIN singer  ON song.id_singer = singer.id" +
            " WHERE\n" +
            "lower(song.`name`) LIKE '%s' \n" +
            "OR lower(author.`name`) LIKE '%s' " +
            "OR  lower(category.`name`) LIKE '%s' " +
            "OR  lower(singer.`name`) LIKE '%s' " +
            " order by %s %s LIMIT %d OFFSET %d  ;\n";
    private final String TOTAL_SONG = "SELECT \n" +
            "    COUNT(1) as total \n" +
            "FROM song \n" +
            "LEFT JOIN author  ON song.id_author = author.id\n" +
            "LEFT JOIN category  ON song.id_category = category.id\n" +
            "LEFT JOIN singer  ON song.id_singer = singer.id" +
            " WHERE\n" +
            "lower(song.`name`) LIKE ? \n" +
            "OR lower(author.`name`) LIKE ? " +
            "OR  lower(category.`name`) LIKE ? " +
            "OR  lower(singer.`name`) LIKE ? ";
    private final String SHOW_LEADERBOARD = " SELECT song.*, count(*) AS views_count\n" +
            "    FROM song\n" +
            "    LEFT JOIN `history` h ON song.id = h.id_song\n" +
            "    GROUP BY song.id\n" +
            "    ORDER BY views_count DESC LIMIT 12;";

    public List<Song> showFilter(Pageable pageable, int filterAuthor, int filterSinger, int filterCategory) {
        List<FieldFilter> fieldFilters = new ArrayList<>();
        if(filterAuthor != 0){
            fieldFilters.add(new FieldFilter("a", filterAuthor));
        }
        if(filterSinger != 0){
            fieldFilters.add(new FieldFilter("sg", filterSinger));
        }
        if(filterCategory != 0){
            fieldFilters.add(new FieldFilter("c", filterCategory));
        }

        String search = pageable.getSearch();
        if (search == null)
            search = "";
        search = "%" + search + "%";
        String select = SELECT_ALL_SONG_WITH_FILTER;
        if (!checkAllFieldSelected(fieldFilters)) {
            select += " where ";
            for (int i = 0; i < fieldFilters.size(); i++) {
                FieldFilter item = fieldFilters.get(i);
                if (item.getValue() != -1) {
                    select += String.format(" %s.id = %s ", item.getField(), item.getValue());
                    if (i != fieldFilters.size() - 1) {
                        select += " AND ";
                    }
                }
            }
        }
//        System.out.println(select);
        List<Song> songs = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = getConnection().prepareStatement(select);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("link_image");
                String song = rs.getString("link_song");
                int authorID = rs.getInt("id_author");
                String author = rs.getString("name_author");
                Author authors = new Author(authorID, author);
                int categoryID = rs.getInt("id_category");
                String type = rs.getString("type");
                Category category = new Category(categoryID, type);
                int singerID = rs.getInt("id_singer");
                String singers = rs.getString("name_singer");
                Singer singer = new Singer(singerID, singers);
                songs.add(new Song(id, name, authors, category, singer, song, image));
            }
            connection.close();
            PreparedStatement statementTotalUsers = connection.prepareStatement(TOTAL_SONG);
            statementTotalUsers.setString(1, search);
            statementTotalUsers.setString(2, search);
            statementTotalUsers.setString(3, search);
            statementTotalUsers.setString(4, search);
            ResultSet rsTotalSong = statementTotalUsers.executeQuery();
            while (rsTotalSong.next()) {
                double totalSong = rsTotalSong.getDouble("total");
                double totalItems = Double.parseDouble(pageable.getTotalItems() + "");
                int totalPages = (int)
                        Math.ceil(totalSong / totalItems);
                pageable.setTotalPage(totalPages);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return songs;
    }

    public boolean checkAllFieldSelected(List<FieldFilter> fieldFilters) {
        for (FieldFilter item : fieldFilters) {
            if (item.getValue() != -1) {
                return false;
            }
        }
        return true;
    }

    public List<Song> findAll(Pageable pageable) {
        List<Song> songs = new ArrayList<>();
        String search = pageable.getSearch();
        if (search == null)
            search = "";
        search = "%" + search + "%";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(String.format(SELECT_ALL_SONG, search, search, search, search,
                             pageable.getNameField(), pageable.getSortBy(),
                             pageable.getTotalItems(), (pageable.getPage() - 1) * pageable.getTotalItems()));) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("link_image");
                String song = rs.getString("link_song");
                int authorID = rs.getInt("id_author");
                String author = rs.getString("name_author");
                Author authors = new Author(authorID, author);
                int categoryID = rs.getInt("id_category");
                String type = rs.getString("type");
                Category category = new Category(categoryID, type);
                int singerID = rs.getInt("id_singer");
                String singers = rs.getString("name_singer");
                Singer singer = new Singer(singerID, singers);
                songs.add(new Song(id, name, authors, category, singer, song, image));

            }
            PreparedStatement statementTotalUsers = connection.prepareStatement(TOTAL_SONG);
            statementTotalUsers.setString(1, search);
            statementTotalUsers.setString(2, search);
            statementTotalUsers.setString(3, search);
            statementTotalUsers.setString(4, search);
            ResultSet rsTotalSong = statementTotalUsers.executeQuery();
            while (rsTotalSong.next()) {
                double totalSong = rsTotalSong.getDouble("total");
                double totalItems = Double.parseDouble(pageable.getTotalItems() + "");
                int totalPages = (int)
                        Math.ceil(totalSong / totalItems);
                pageable.setTotalPage(totalPages);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return songs;
    }



    public Song findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_USER_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);

            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idSong = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("link_image");
                String song = rs.getString("link_song");
                int authorID = rs.getInt("id_author");
                String author = rs.getString("name_author");
                Author authors = new Author(authorID, author);
                int categoryID = rs.getInt("id_category");
                String type = rs.getString("type");
                Category category = new Category(categoryID, type);
                int singerID = rs.getInt("id_singer");
                String singers = rs.getString("name_singer");
                Singer singer = new Singer(singerID, singers);
                return new Song(idSong, name, song, image, authors, category, singer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void createSong(Song song) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, song.getName());
            preparedStatement.setInt(2, song.getAuthor().getId());
            preparedStatement.setInt(3, song.getCategory().getId());
            preparedStatement.setInt(4, song.getSinger().getId());
            preparedStatement.setString(5, song.getSong());
            preparedStatement.setString(6, song.getImage());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int songId = generatedKeys.getInt(1);
                song.setId(songId);

                insertStatement.setInt(1, song.getId());
                insertStatement.setString(2, song.getSong());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveSongToDatabase(Song song) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, song.getName());
            preparedStatement.setInt(2, song.getAuthor().getId());
            preparedStatement.setInt(3, song.getCategory().getId());
            preparedStatement.setInt(4, song.getSinger().getId());
            preparedStatement.setString(5, song.getSong());
            preparedStatement.setString(6, song.getImage());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editSong(Song song) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER)) {
            preparedStatement.setString(1, song.getName());
            preparedStatement.setInt(2, song.getAuthor().getId());
            preparedStatement.setInt(3, song.getCategory().getId());
            preparedStatement.setInt(4, song.getSinger().getId());
            preparedStatement.setString(5, song.getSong());
            preparedStatement.setString(6, song.getImage());
            preparedStatement.setInt(7, song.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBook(int id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Song> showLeaderboard(){
        List<Song> songs = new ArrayList<>();
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SHOW_LEADERBOARD);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int idAuthor = rs.getInt("id_author");
                int idCategory = rs.getInt("id_category");
                int idSinger = rs.getInt("id_singer");
                String linkImg = rs.getString("link_image");
                String linkSong = rs.getString("link_song");
                Author author = authorService.findByID(idAuthor);
                Category category = categoryService.findByID(idCategory);
                Singer singer = singerService.findByID(idSinger);
                songs.add(new Song(id,name,author,category,singer,linkSong,linkImg));
            }
            return songs;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
