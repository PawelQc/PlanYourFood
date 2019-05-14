package pl.qceyco.web.loggedUser.recipePlan;

import pl.qceyco.dao.RecipePlanDao;
import pl.qceyco.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/delete-from-plan")
public class RecipeDeleteFromPlanServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipePlanId = request.getParameter("recipePlanId");
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        RecipePlan recipePlan = recipePlanDao.getRecipePlanById(Integer.parseInt(recipePlanId));
        recipePlanDao.deleteRecipePlan(recipePlan);
        request.setAttribute("successfulDeletion", "Przepis usunięto z planu!");
        getServletContext().getRequestDispatcher("/app/plan/details?planId=" + recipePlan.getPlan().getId()).forward(request, response);
    }
}
