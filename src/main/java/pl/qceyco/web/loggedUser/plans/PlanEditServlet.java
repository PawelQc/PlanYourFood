package pl.qceyco.web.loggedUser.plans;

import org.apache.commons.lang3.StringUtils;
import pl.qceyco.dao.PlanDao;
import pl.qceyco.model.Admin;
import pl.qceyco.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/plan/edit")
public class PlanEditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        req.setAttribute("admin", loggedAdmin);
        String planId = req.getParameter("planId");
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.getPlanById(Integer.parseInt(planId));
        req.setAttribute("plan", plan);
        getServletContext().getRequestDispatcher("/plans/planEditForm.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("planName");
        String description = req.getParameter("planDescription");
        if (StringUtils.isBlank(name) || StringUtils.isBlank(description)) {
            req.setAttribute("errorNotCompleteData", "Nie podano wszystkich danych!");
            doGet(req, resp);
            return;
        }
        String planId = req.getParameter("planId");
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.getPlanById(Integer.parseInt(planId));
        plan.setName(name);
        plan.setDescription(description);
        planDao.updatePlan(plan);
        resp.sendRedirect("/app/plan/list");
    }
}
