package pl.qceyco.web.loggedUser.recipePlan;


import org.apache.commons.lang3.StringUtils;
import pl.qceyco.dao.DayNameDao;
import pl.qceyco.dao.PlanDao;
import pl.qceyco.dao.RecipeDao;
import pl.qceyco.dao.RecipePlanDao;
import pl.qceyco.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/app/recipe/add-to-plan")
public class RecipeAddToPlanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planId = request.getParameter("planId");
        String recipeId = request.getParameter("recipeId");
        String dayId = request.getParameter("dayId");
        String mealName = request.getParameter("mealName");
        String mealNumber = request.getParameter("mealNumber");
        if (StringUtils.isBlank(planId) || StringUtils.isBlank(recipeId) || StringUtils.isBlank(dayId)
                || StringUtils.isBlank(mealName) || StringUtils.isBlank(mealNumber)) {
            request.setAttribute("errorNotCompleteData", "Nie podano wszystkich danych!");
            doGet(request, response);
            return;
        }
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.getPlanById(Integer.parseInt(planId));
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.getRecipeById(Integer.parseInt(recipeId));
        DayNameDao dayNameDao = new DayNameDao();
        DayName dayName = dayNameDao.getDayNameById(Integer.parseInt(dayId));
        RecipePlan recipePlan = new RecipePlan(recipe, mealName, Integer.parseInt(mealNumber), dayName, plan);
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.createRecipePlan(recipePlan);
        response.sendRedirect("/app/dashboard");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        request.setAttribute("admin", loggedAdmin);
        PlanDao planDao = new PlanDao();
        List<Plan> plans = planDao.getAllPlansbyAdminId(loggedAdmin.getId());
        request.setAttribute("plans", plans);
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipes = recipeDao.getAllRecipesByAdminId(loggedAdmin.getId());
        request.setAttribute("recipes", recipes);
        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> days = dayNameDao.getAllDays();
        request.setAttribute("days", days);
        getServletContext().getRequestDispatcher("/recipes/recipeAddToPlanForm.jsp").forward(request, response);
    }
}
