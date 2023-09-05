package controller;

import dto.Pageable;
import model.User;
import service.UserService;
import model.Role;
import model.User;
import service.RoleService;
import service.UserService;
import utils.PasswordEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/users")
public class UserServlet extends HttpServlet {
    RoleService roleService = new RoleService();
    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                editUser(req, resp);
                break;
            default:
                showUser(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                showEditUser(req,resp);
                break;
            case "delete":
                deleteUser(req,resp);
                break;
            default:
                showUser(req,resp);
        }
    }

    private void showEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userService.findById(id);
        req.setAttribute("user",user);
        req.setAttribute("roles",roleService.findAll());
        req.getRequestDispatcher("/edituser.jsp").forward(req,resp);
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int roleId = Integer.parseInt(req.getParameter("role"));

        Role role = roleService.findById(roleId);
        User user = new User(id,username,PasswordEncoder.encode(password),role);
        userService.updateUser(user);

        req.setAttribute("user",user);
        req.setAttribute("roles", roleService.findAll());
        req.setAttribute("message", "edited");
        req.getRequestDispatcher("/edituser.jsp").forward(req,resp);
    }


    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteUser(id);
        showUser(req,resp);
    }

    private void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");

        int page = 1;
        if(req.getParameter("page") != null){
            page = Integer.parseInt(req.getParameter("page"));
        }
        String sortBy = req.getParameter("sortby");
        if(sortBy == null){
            sortBy = "asc";
        }
        String nameField = req.getParameter("nameField");
        if(nameField == null){
            nameField = "user.id";
        }
        Pageable pageable = new Pageable(search, page, 5 , nameField, sortBy);
        List<User> users = userService.findAll(pageable);
        req.setAttribute("users",users);
        req.setAttribute("pageable", pageable);
        req.getRequestDispatcher("/JSPuser/users.jsp").forward(req,resp);

    }

    private void signup(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void updateInfo(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.login(username, password);
        if (user == null) {
//            req.setAttribute("error", "Tài khoản không tồn tại hoặc mật khẩu không đúng !");
            req.getRequestDispatcher("/JSPurer/signin.jsp").forward(req, resp);
//            response.sendRedirect("user?action=login");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/home");
        }
    }

        private void showCreate (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            req.setAttribute("roles", roleService.findAll());
            req.getRequestDispatcher("/JSPuser/signup.jsp").forward(req, resp);
        }

        private void createUser (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            String username = req.getParameter("username");
            String password = PasswordEncoder.encode(req.getParameter("password"));
            int roleId = Integer.parseInt(req.getParameter("role_id"));
            Role role = roleService.findById(roleId);
            User user = new User(username, password, role);
            userService.createUser(user);
            req.setAttribute("user", user);
            req.setAttribute("message", "Created");
            req.setAttribute("roles", roleService.findAll());
            req.getRequestDispatcher("/JSPuser/signup.jsp").forward(req, resp);

        }

}