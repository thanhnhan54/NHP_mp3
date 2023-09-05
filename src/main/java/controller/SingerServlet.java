package controller;

import model.Category;
import model.Singer;
import service.SingerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SingerServlet", urlPatterns = "/admin/singers")
public class SingerServlet extends HttpServlet {
    SingerService singerService = new SingerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateSinger(req,resp);
                break;
            case "edit":
                showEditSinger(req,resp);
                break;
            case "delete":
                break;
            default:
                showSinger(req,resp);
        }
    }

    private void showEditSinger(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Singer singers = singerService.findByID(id);
        req.setAttribute("singers",singers);
        req.getRequestDispatcher("/JSPsinger/editSinger.jsp").forward(req,resp);
    }
    public void editSinger(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Singer singer = new Singer(id,name);
        singerService.editSinger(singer);
        request.setAttribute("message","edit thành công");
        request.setAttribute("singer",singer);
        request.getRequestDispatcher("/JSPsinger/editSinger.jsp").forward(request,response);
    }
    private void showCreateSinger(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Singer> singers = singerService.findAll();
        req.setAttribute("singers",singers);
        req.getRequestDispatcher("/JSPsinger/createSinger.jsp").forward(req,resp);
    }
    public void createSinger(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Singer singer = new Singer(name);
        singerService.createSinger(singer);
        request.setAttribute("message","create thành công");
        request.setAttribute("singer",singer);
        request.getRequestDispatcher("/JSPsinger/createSinger.jsp").forward(request,response);
    }

    private void showSinger(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Singer> singers = singerService.findAll();
        req.setAttribute("singers",singers);
        req.getRequestDispatcher("/JSPsinger/singer.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createSinger(req,resp);
                break;
            case "edit":
                editSinger(req,resp);
                break;
            default:
                showSinger(req,resp);
        }
    }
}
