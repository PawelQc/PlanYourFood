package pl.qceyco.web.mainSite;

import pl.qceyco.dao.RecipeDao;
import pl.qceyco.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/recipes")
public class AllRecipesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecipeDao dao = new RecipeDao();
        List<Recipe> recipeList = dao.getAllRecipes();
        req.setAttribute("recipes", recipeList);
        getServletContext().getRequestDispatcher("/mainSite/allRecipes.jsp")
                .forward(req, resp);
    }
}
