package pl.qceyco.web;

import pl.qceyco.dao.AdminDao;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/app-add-schedule.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("planName");
        String description = req.getParameter("planDescription");

        HttpSession session = req.getSession();
        int id = (int) session.getAttribute("adminID");

        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.getAdminById(id);

        PlanDao planDao = new PlanDao();

        planDao.createPlan(new Plan(name,description,admin));

        resp.sendRedirect("/app/plan/list");

    }
}
