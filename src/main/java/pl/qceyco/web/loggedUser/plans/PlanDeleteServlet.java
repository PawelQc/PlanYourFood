package pl.qceyco.web.loggedUser.plans;

import pl.qceyco.dao.PlanDao;
import pl.qceyco.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/delete")
public class PlanDeleteServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String planId = request.getParameter("planId");
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.getPlanById(Integer.parseInt(planId));
        planDao.deletePlan(plan);
        request.setAttribute("successfulDeletion", "Plan usunięto!");
        getServletContext().getRequestDispatcher("/app/plan/list").forward(request, response);
    }
}
