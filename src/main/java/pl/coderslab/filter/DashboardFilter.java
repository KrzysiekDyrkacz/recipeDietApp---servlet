package pl.coderslab.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "ServletFilter", urlPatterns = "/dashboard.jsp")

/**
 * This class is blocking all users who tries enter on "dashboard.jsp" without login.
 */
public class DashboardFilter implements Filter {
    private String charsetEncoding = "utf-8";
    private String contentType = "text/html";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding(charsetEncoding);
        response.setContentType(contentType);
        response.setCharacterEncoding(charsetEncoding);
        HttpSession session = request.getSession();
        if (session.getAttribute("Login") == "on") {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    }
    public void init(FilterConfig config) throws ServletException {
    }
}