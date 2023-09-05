package service;

import dao.PlaylistDAO;
import model.ISP;
import model.Playlist;
import model.Song;

import java.util.List;

public class PlaylistService {
    PlaylistDAO playlistDAO = new PlaylistDAO();
    public void createPlaylist(Playlist playlist){
        playlistDAO.createPlaylist(playlist);
    }
    public List<Song> findSongsByPlaylist(int id){
        return  playlistDAO.findSongsByPlaylist(id);
    }
    public List<Playlist> findUserPlaylists(int id){
        return playlistDAO.findUserPlaylists(id);
    }
    public void createISP(ISP isp){
        playlistDAO.createISP(isp);
    }
    public void deletePlaylist(int id){
        playlistDAO.deletePlaylist(id);
    }
    public void deleteISP(int id){
        playlistDAO.deleteISP(id);
    }
    public Playlist findById(int id){
        return playlistDAO.findById(id);
    }
}
