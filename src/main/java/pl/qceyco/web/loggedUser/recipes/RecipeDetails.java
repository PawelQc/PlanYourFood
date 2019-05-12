package pl.qceyco.web.loggedUser.recipes;

import pl.qceyco.dao.RecipeDao;
import pl.qceyco.model.Admin;
import pl.qceyco.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/recipe/details")
public class RecipeDetails extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        req.setAttribute("admin", loggedAdmin);
        String recipeId = req.getParameter("recipeId");
        RecipeDao dao = new RecipeDao();
        Recipe recipe = dao.getRecipeById(Integer.parseInt(recipeId));
        req.setAttribute("recipe", recipe);
        String[] ingredients = recipe.getIngredients().split(",");
        req.setAttribute("ingredients", ingredients);
        getServletContext().getRequestDispatcher("/recipes/recipeDetails.jsp")
                .forward(req, resp);
    }
}