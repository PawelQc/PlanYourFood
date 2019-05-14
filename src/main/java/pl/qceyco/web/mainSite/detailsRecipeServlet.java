package pl.qceyco.web.mainSite;

import pl.qceyco.dao.RecipeDao;
import pl.qceyco.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipes/details")
public class detailsRecipeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recipeId = req.getParameter("recipeId");
        RecipeDao dao = new RecipeDao();
        Recipe recipe = dao.getRecipeById(Integer.parseInt(recipeId));
        req.setAttribute("recipe", recipe);
        String[] ingredients = recipe.getIngredients().split(",");
        req.setAttribute("ingredients", ingredients);
        getServletContext().getRequestDispatcher("/mainSite/detailsRecipe.jsp")
                .forward(req, resp);
    }
}