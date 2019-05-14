package pl.qceyco.web.loggedUser.recipes;

import org.apache.commons.lang3.StringUtils;
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

@WebServlet("/app/recipe/edit")
public class RecipeEditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        req.setAttribute("admin", loggedAdmin);
        String recipeId = req.getParameter("recipeId");
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.getRecipeById(Integer.parseInt(recipeId));
        req.setAttribute("recipe", recipe);
        getServletContext().getRequestDispatcher("/recipes/recipeEditForm.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String preparationTime = req.getParameter("preparationTime");
        String preparation = req.getParameter("preparation");
        String ingredients = req.getParameter("ingredients");
        String recipeId = req.getParameter("recipeId");
        if (StringUtils.isBlank(name) || StringUtils.isBlank(description) || StringUtils.isBlank(preparationTime)
                || StringUtils.isBlank(preparation) || StringUtils.isBlank(ingredients)) {
            req.setAttribute("errorNotCompleteData", "Nie podano wszystkich danych!");
            doGet(req, resp);
            return;
        }
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.getRecipeById(Integer.parseInt(recipeId));
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setPreparationTime(Integer.parseInt(preparationTime));
        recipe.setPreparation(preparation);
        recipe.setIngredients(ingredients);
        recipeDao.updateRecipe(recipe);
        resp.sendRedirect("/app/recipe/list");
    }
}
