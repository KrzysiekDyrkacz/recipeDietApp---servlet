package pl.coderslab.web.app.recipe;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "AddNewRecipeServlet", urlPatterns = {"/app/recipe/add"})
public class AddRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app-add-recipe.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String recipeName = req.getParameter("name");
        String recipeDescription = req.getParameter("recipeDescription");
        int cookTime = Integer.parseInt(req.getParameter("cookTime"));
        String wayToCook = req.getParameter("wayToCook");
        String ingredients = req.getParameter("ingredients");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(String.valueOf(session.getAttribute("adminId")));
        Recipe recipe = new Recipe(recipeName, ingredients, recipeDescription, LocalDateTime.now(), LocalDateTime.now(), cookTime, wayToCook, adminId);
        new RecipeDao().create(recipe);
        response.sendRedirect(req.getContextPath()+"/app/recipe/list");
    }
}

