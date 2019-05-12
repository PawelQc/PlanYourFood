package pl.qceyco.web.loggedUser.recipes;

import pl.qceyco.dao.RecipeDao;
import pl.qceyco.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/delete")
public class RecipeDeleteServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String recipeId = request.getParameter("recipeId");
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.getRecipeById(Integer.parseInt(recipeId));
        recipeDao.deleteRecipe(recipe);
        request.setAttribute("successfulDeletion", "Przepis usunięto!");
        getServletContext().getRequestDispatcher("/app/recipe/list").forward(request, response);
    }
}
