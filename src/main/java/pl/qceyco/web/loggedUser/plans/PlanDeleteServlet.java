package pl.qceyco.web.loggedUser.plans;

import pl.qceyco.dao.PlanDao;
import pl.qceyco.dao.RecipePlanDao;
import pl.qceyco.model.Plan;
import pl.qceyco.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/delete")
public class PlanDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planId = request.getParameter("planId");
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.getPlanById(Integer.parseInt(planId));
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        List<RecipePlan> recipePlans = recipePlanDao.getAllRecipePlans();
        boolean isPlanConnectedWithOtherRecordsInDB = false;
        for (RecipePlan rP : recipePlans) {
            if (rP.getPlan().getId() == plan.getId()) {
                isPlanConnectedWithOtherRecordsInDB = true;
                break;
            }
        }
        if (isPlanConnectedWithOtherRecordsInDB == true) {
            request.setAttribute("cannotDelete", "Plan nie może zostać usunięty - usuń najpierw przypisane do planu przepisy!");
            getServletContext().getRequestDispatcher("/app/plan/list").forward(request, response);
            return;
        }
        planDao.deletePlan(plan);
        request.setAttribute("successfulDeletion", "Plan usunięto!");
        getServletContext().getRequestDispatcher("/app/plan/list").forward(request, response);
    }
}
