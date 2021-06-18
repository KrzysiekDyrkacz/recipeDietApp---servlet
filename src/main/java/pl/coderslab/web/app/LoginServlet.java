package pl.coderslab.web.app;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession();
        if (sessionUser.getAttribute("Login") == "on") {
            response.sendRedirect(request.getContextPath()+"/app");
        } else {
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AdminDao adminDao = new AdminDao();
        if (adminDao.validationData(new Admin(email, password))) {
            Admin admins = adminDao.readByEmail(email);
            if(adminDao.validationStatus(admins.getId())) {
                HttpSession session = request.getSession();
                session.setAttribute("Login", "on");
                session.setAttribute("adminId", admins.getId());
                session.setAttribute("nameUser", admins.getFirstName() + " " + admins.getLastName());
                session.setAttribute("superAdmin", admins.getSuperAdmin());
                session.setMaxInactiveInterval(60 * 60 * 24);
                response.sendRedirect(request.getContextPath() + "/app");
            }else {
                request.getRequestDispatcher("/blocked-user.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }
}
