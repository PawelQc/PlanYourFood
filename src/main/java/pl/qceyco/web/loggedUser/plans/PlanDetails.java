package pl.qceyco.web.loggedUser.plans;

import pl.qceyco.dao.PlanDao;
import pl.qceyco.dao.RecipePlanDao;
import pl.qceyco.model.Admin;
import pl.qceyco.model.Plan;
import pl.qceyco.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/app/plan/details")
public class PlanDetails extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        req.setAttribute("admin", loggedAdmin);
        String planId = req.getParameter("planId");
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        List<RecipePlan> planDetails = recipePlanDao.getRecipePlanDetailAsList(Integer.parseInt(planId));
        req.setAttribute("planDetails", planDetails);

        PlanDao planDao = new PlanDao();
        Plan plan = planDao.getPlanById(Integer.parseInt(planId));
        req.setAttribute("plan", plan);

        List<Integer>list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        req.setAttribute("list", list);


        getServletContext().getRequestDispatcher("/plans/planDetails.jsp")
                .forward(req, resp);
    }
}