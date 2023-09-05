package controller;

import model.Category;
import service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/admin/categorys")

public class CategoryServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateCategory(req,resp);
                break;
            case "edit":
                showEditCategory(req,resp);
                break;
            case "delete":
                deleteCategory(req,resp);
                break;
            default:
                showCategory(req,resp);
        }
    }

    private void showEditCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category category = categoryService.findByID(id);
        req.setAttribute("category",category);
        req.getRequestDispatcher("/JSPcategory/editCategory.jsp").forward(req,resp);
    }
    public void editCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Category category = new Category(id,name);
        categoryService.editCategory(category);
        request.setAttribute("message","edit thành công");
        request.setAttribute("category",category);
        request.getRequestDispatcher("/JSPcategory/editCategory.jsp").forward(request,response);
    }

    private void showCreateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categorys = categoryService.findAll();
        req.setAttribute("categorys",categorys);
        req.getRequestDispatcher("/JSPcategory/createCategory.jsp").forward(req,resp);
    }

    private void createCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Category category = new Category(name);
        categoryService.createCategory(category);
        req.setAttribute("message", "create thành công");
        req.setAttribute("category",category);
        req.getRequestDispatcher("/JSPcategory/createCategory.jsp").forward(req,resp);
    }
    public void deleteCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryService.deleteCategory(id);
        showCategory(request,response);
    }

    private void showCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categorys = categoryService.findAll();
        req.setAttribute("categorys",categorys);
        req.getRequestDispatcher("/JSPcategory/category.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createCategory(req,resp);
                break;
            case "edit":
                editCategory(req,resp);
                break;
            default:
                showCategory(req,resp);
        }
    }

}
