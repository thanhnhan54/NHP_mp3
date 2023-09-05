package controller;

import model.Author;
import service.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "authorService", urlPatterns = "/admin/authors")
public class AuthorServlet extends HttpServlet {
    AuthorService authorService = new AuthorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreatAuthor(req,resp);
                break;
            case"edit":
                showEditAuthor(req,resp);
                break;
            case "delete":
                deleteAuthor(req,resp);
                break;
            default:
                showAuthor(req,resp);
        }
    }

    private void showEditAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Author authors = authorService.findByID(id);
        req.setAttribute("authors",authors);
        req.getRequestDispatcher("/JSPauthor/editAuthor.jsp").forward(req,resp);
    }
    public void editAuthor(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Author author = new Author(id,name);
        authorService.editAuthor(author);
        request.setAttribute("message","edit thành công");
        request.setAttribute("author",author);
        request.getRequestDispatcher("/JSPauthor/editAuthor.jsp").forward(request,response);
    }
    private void showCreatAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorService.findAll();
        req.setAttribute("authors",authors);
        req.getRequestDispatcher("/JSPauthor/createAuthor.jsp").forward(req,resp);
    }
    public void createAuthor(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Author author = new Author(name);
        authorService.createAuthor(author);
        request.setAttribute("message", "Thêm thành công");
        request.setAttribute("author",author);
        request.getRequestDispatcher("/JSPauthor/createAuthor.jsp").forward(request,response);
    }

    private void showAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorService.findAll();
        req.setAttribute("authors",authors);
        req.getRequestDispatcher("/JSPauthor/author.jsp").forward(req,resp);
    }
    public void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        authorService.deleteAuthor(id);
        showAuthor(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createAuthor(req,resp);
                break;
            case "edit":
                editAuthor(req,resp);
                break;
            default:
                showAuthor(req,resp);
        }
    }

}
