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
import java.util.List;

@WebServlet("/app/plan/list")
public class SchedulesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int adminID = (int) session.getAttribute("adminID");
        System.out.println("adminID: " + adminID);

        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.getAdminById(adminID);
        request.setAttribute("admin", admin);

        PlanDao planDao = new PlanDao();
        List<Plan> planList = planDao.getAllPlansbyAdminId(adminID);
        request.setAttribute("plans", planList);
        getServletContext().getRequestDispatcher("/app-plans.jsp")
                .forward(request, response);
    }
}
