package pl.qceyco.web.loggedUser.recipes;

import pl.qceyco.dao.RecipeDao;
import pl.qceyco.dao.RecipePlanDao;
import pl.qceyco.model.Recipe;
import pl.qceyco.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/delete")
public class RecipeDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipeId = request.getParameter("recipeId");
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.getRecipeById(Integer.parseInt(recipeId));
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        List<RecipePlan> recipePlans = recipePlanDao.getAllRecipePlans();
        boolean isRecipeConnectedWithOtherRecordsInDB = false;
        for (RecipePlan rP : recipePlans) {
            if (rP.getRecipe().getId() == recipe.getId()) {
                isRecipeConnectedWithOtherRecordsInDB = true;
                break;
            }
        }
        if (isRecipeConnectedWithOtherRecordsInDB == true) {
            request.setAttribute("cannotDelete", "Przepis nie może zostać usunięty - usuń najpierw przepis z planu!");
            getServletContext().getRequestDispatcher("/app/recipe/list").forward(request, response);
            return;
        }
        recipeDao.deleteRecipe(recipe);
        request.setAttribute("successfulDeletion", "Przepis usunięto!");
        getServletContext().getRequestDispatcher("/app/recipe/list").forward(request, response);
    }

}
