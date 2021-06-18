package pl.coderslab.web.app;

import pl.coderslab.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SuperAdminUserServlet", urlPatterns = {"/app/super/admin"})
public class SuperAdminUserServlet extends HttpServlet {
    private static final int STATS_ENABLE_USER=1;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int enable = Integer.parseInt(session.getAttribute("superAdmin").toString());
        if (enable == STATS_ENABLE_USER) {
            request.setAttribute("users", new AdminDao().findAll());
            request.getRequestDispatcher("/super-admin-users.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/app");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("userId"));
        new AdminDao().updateEnable(id, 0);
        response.sendRedirect(request.getContextPath() + "/app/super/admin");
    }
}
