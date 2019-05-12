package pl.qceyco.web.loggedUser;

import pl.qceyco.dao.PlanDao;
import pl.qceyco.dao.RecipeDao;
import pl.qceyco.dao.RecipePlanDao;
import pl.qceyco.model.Admin;
import pl.qceyco.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/dashboard")
public class DashboardServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        request.setAttribute("admin", loggedAdmin);

        RecipeDao recipeDao = new RecipeDao();
        int numberOfRecipes = recipeDao.countNumberOfRecipesByAdminId(loggedAdmin.getId());
        request.setAttribute("numberOfRecipes", numberOfRecipes);

        PlanDao planDao = new PlanDao();
        int numberOfPlans = planDao.countNumberOfPlansByAdminId(loggedAdmin.getId());
        request.setAttribute("numberOfPlans", numberOfPlans);

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        RecipePlan recipePlan = recipePlanDao.getRecentRecipePlanByAdminId(loggedAdmin.getId());
        request.setAttribute("recipePlan", recipePlan);

        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}
