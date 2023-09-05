package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Pageable;
import model.History;
import model.Song;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "homeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    AuthorService authorService = new AuthorService();
    UserService userService = new UserService();
    SingerService singerService = new SingerService();
    CategoryService categoryService = new CategoryService();
    SongService songService = new SongService();

    HistoryService  historyService = new HistoryService();
    private int TOTAL_ITEMS = 10;
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Pageable pageable = new Pageable();
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            session.setAttribute("user",userService.findByUsername("guest@gmail.com"));
        }
        List<History> histories = historyService.findAll();
        req.setAttribute("histories",histories);
        req.setAttribute("authors", authorService.findAll());
        req.setAttribute("singers", singerService.findAll());
        req.setAttribute("categories", categoryService.findAll());
        req.setAttribute("leaderboard", songService.showLeaderboard());
        req.setAttribute("songsJSON",convertListToJson(songService.showLeaderboard()));
        req.getRequestDispatcher("/JSPhomeUser/home.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "logout":
                logout(req,resp);
                break;
            default:
                processRequest(req,resp);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("/home");
    }
    public static String convertListToJson(List<?> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
