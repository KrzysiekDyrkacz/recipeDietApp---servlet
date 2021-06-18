package pl.coderslab.web.app.recipe;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "EditRecipeServlet", urlPatterns = {"/app/edit/recipe"})
public class EditRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("recipe", new RecipeDao().readByAdminId(Integer.parseInt(request.getParameter("id"))));
        getServletContext().getRequestDispatcher("/app-recipe-edit.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String recipeName = request.getParameter("recipeName");
        String recipeDescripiton = request.getParameter("recipeDescripiton");
        String recipePreparationTime = request.getParameter("recipePreparationTime");
        String recipePreparation = request.getParameter("recipePreparation");
        String recipeIngredients = request.getParameter("recipeIngredients");
        LocalDateTime update = LocalDateTime.now();
        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        recipe.setDescription(recipeDescripiton);
        recipe.setPreparationTime(Integer.parseInt(recipePreparationTime));
        recipe.setPreparation(recipePreparation);
        recipe.setIngredients(recipeIngredients);
        recipe.setUpdated(update);
        recipe.setId(Integer.parseInt(id));
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.update(recipe);
        response.sendRedirect(request.getContextPath()+"/app/recipe/list");
    }
}