package pl.qceyco.web.loggedUser.plans;

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
import java.util.List;

@WebServlet("/app/plan/list")
public class PlansAllServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        request.setAttribute("admin", loggedAdmin);

        PlanDao planDao = new PlanDao();
        List<Plan> planList = planDao.getAllPlansbyAdminId(loggedAdmin.getId());
        request.setAttribute("plans", planList);
        getServletContext().getRequestDispatcher("/plans/plansAll.jsp")
                .forward(request, response);
    }
}
