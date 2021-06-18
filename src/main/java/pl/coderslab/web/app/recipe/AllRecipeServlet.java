package pl.coderslab.web.app.recipe;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is using for searching recipes by name.
 * And typing by first letters of recipe and it looks for similarity.
 * */
@WebServlet(name = "AllRecipeServlet", urlPatterns = {"/recipes"})
public class AllRecipeServlet extends HttpServlet {

    private static final String RETURN_ALL_RECIPES = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("recipes", new RecipeDao().findAll());
        request.getRequestDispatcher("/recipes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("recipeName");
        if(!status.equals(RETURN_ALL_RECIPES)){
            String recipeName = request.getParameter("recipeName");
            request.setAttribute("recipes", new RecipeDao().findAllByName(recipeName));
            request.getRequestDispatcher("/recipes.jsp").forward(request, response);
        }else {
            request.setAttribute("recipes", new RecipeDao().findAll());
            request.getRequestDispatcher("/recipes.jsp").forward(request, response);
        }
    }
}
