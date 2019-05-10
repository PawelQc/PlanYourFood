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
import java.util.List;


@WebServlet("/app/recipe/list")
public class RecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        int adminIDInt = (int) session.getAttribute("adminID");

        int id = (int) session.getAttribute("adminID");         //ten fragment dodaje na górze imię admina
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.getAdminById(id);
        req.setAttribute("admin", admin);

        RecipeDao dao = new RecipeDao();
        List<Recipe> recipeList = dao.getAllRecipesByAdminId(adminIDInt);
        req.setAttribute("recipes", recipeList);
        getServletContext().getRequestDispatcher("/app-recipes.jsp")
                .forward(req, resp);
    }
}
