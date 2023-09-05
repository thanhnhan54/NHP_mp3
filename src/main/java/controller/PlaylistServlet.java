package controller;

import model.Like;
import model.Playlist;
import model.Song;
import model.User;
import service.PlaylistService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "playlistServlet", urlPatterns = "/playlists")
public class PlaylistServlet extends HttpServlet {
    PlaylistService playlistService = new PlaylistService();
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "showListSong":
                showListSong(req,resp);
                break;
            default:
                showPlaylist(req,resp);
        }
    }

    private void showListSong(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         int id = Integer.parseInt(req.getParameter("id"));
         List<Song> songs = playlistService.findSongsByPlaylist(id);
         Playlist playlist = playlistService.findById(id);
         req.setAttribute("songs", songs);
         req.setAttribute("playlist", playlist);
         req.getRequestDispatcher("/JSPhomeUser/userplaylist.jsp").forward(req,resp);
    }

    private void showPlaylist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int idUser = user.getId();
        List<Playlist> playlists = playlistService.findUserPlaylists(idUser);
        req.setAttribute("playlists", playlists);
        req.getRequestDispatcher("/JSPhomeUser/playlist.jsp").forward(req,resp);
    }


}
