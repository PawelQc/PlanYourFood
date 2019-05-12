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

@WebServlet("/app/plan/add")
public class PlanAddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        req.setAttribute("admin", loggedAdmin);
        getServletContext().getRequestDispatcher("/plans/planAddForm.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("planName");
        String description = req.getParameter("planDescription");
        HttpSession session = req.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        PlanDao planDao = new PlanDao();
        planDao.createPlan(new Plan(name, description, loggedAdmin));
        resp.sendRedirect("/app/plan/list");
    }
}
