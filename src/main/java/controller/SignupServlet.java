package controller;

import com.mysql.cj.Session;
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

@WebServlet(urlPatterns = "/signup")
public class SignupServlet extends HttpServlet {
    UserService userService = new UserService();
    PasswordEncoder passwordEncoder = new PasswordEncoder();
    RoleService roleService = new RoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/JSPhomeUser/signup.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rePass = req.getParameter("re_password");
        boolean redirect = false;
        Role role = roleService.findById(2);
        if (!password.equals(rePass)) {
            redirect = true; // Password không trùng khớp, cần chuyển hướng đến "/signup"
        } else if (userService.checkExist(username)) {
            redirect = true;
        } else {
            String passencoded = passwordEncoder.encode(password);
            User user = new User(username, passencoded, role);
            userService.createUser(user);
            HttpSession session = req.getSession();
            session.setAttribute("role", user.getRole().getName());
            session.setAttribute("user", user);
        }

        if (redirect) {
            resp.sendRedirect("/signup");
        } else {
            resp.sendRedirect("/home");
        }
    }

}
