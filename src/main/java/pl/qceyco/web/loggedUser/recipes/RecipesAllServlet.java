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
import java.util.List;


@WebServlet("/app/recipe/list")
public class RecipesAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        req.setAttribute("admin", loggedAdmin);
        RecipeDao dao = new RecipeDao();
        List<Recipe> recipeList = dao.getAllRecipesByAdminId(loggedAdmin.getId());
        req.setAttribute("recipes", recipeList);
        getServletContext().getRequestDispatcher("/recipes/recipesAll.jsp")
                .forward(req, resp);
    }
}
