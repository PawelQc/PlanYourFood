package pl.qceyco.web;

import pl.qceyco.dao.AdminDao;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();
        int adminIDInt = (int) session.getAttribute("adminID");
        System.out.println("adminID: " + adminIDInt);
        int id = (int) session.getAttribute("adminID");
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.getAdminById(id);
        req.setAttribute("admin", admin);

        String paramString = req.getParameter("param");
        int param = Integer.parseInt(paramString);

        RecipeDao dao = new RecipeDao();
        Recipe recipe = dao.getRecipeById(param);

        req.setAttribute("recipeName", recipe.getName());
        req.setAttribute("recipeDescription", recipe.getDescription());
        req.setAttribute("recipePreparationTime", recipe.getPreparationTime());
        req.setAttribute("recipePreparation", recipe.getPreparation());
        String [] ingredients = recipe.getIngredients().split(", ");

        req.setAttribute("ingredients", ingredients);

        getServletContext().getRequestDispatcher("/recipe-details.jsp")
                .forward(req, resp);
    }
}