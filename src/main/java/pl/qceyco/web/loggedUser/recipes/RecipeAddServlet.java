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


@WebServlet("/app/recipe/add")
public class RecipeAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String preparationTime = request.getParameter("preparationTime");
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");
        if (StringUtils.isBlank(name) || StringUtils.isBlank(description) || StringUtils.isBlank(preparationTime)
                || StringUtils.isBlank(preparation) || StringUtils.isBlank(ingredients)) {
            request.setAttribute("errorNotCompleteData", "Nie podano wszystkich danych!");
            doGet(request, response);
            return;
        }
        HttpSession session = request.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        Recipe recipe = new Recipe(name, ingredients, description, Integer.parseInt(preparationTime), preparation, loggedAdmin);
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.createRecipe(recipe);
        response.sendRedirect("/app/recipe/list");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        request.setAttribute("admin", loggedAdmin);
        getServletContext().getRequestDispatcher("/recipes/recipeAddForm.jsp").forward(request, response);
    }
}
