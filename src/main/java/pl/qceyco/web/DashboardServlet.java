package pl.qceyco.web;

import pl.qceyco.dao.AdminDao;
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
        int id = (int) session.getAttribute("adminID");
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.getAdminById(id);
        request.setAttribute("admin", admin);

        RecipeDao recipeDao = new RecipeDao();
        int numberOfRecipies = recipeDao.countNumberOfRecipesByAdminId(id);
        request.setAttribute("numberOfRecipies", numberOfRecipies);

        PlanDao planDao = new PlanDao();
        int numberOfPlans = planDao.countNumberOfPlansByAdminId(id);
        request.setAttribute("numberOfPlans", numberOfPlans);

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        RecipePlan recipePlan = recipePlanDao.getRecentRecipePlanByAdminiD(id);
        request.setAttribute("recipePlan", recipePlan);

        getServletContext().getRequestDispatcher("/app-dashboard.jsp").forward(request, response);
    }
}
