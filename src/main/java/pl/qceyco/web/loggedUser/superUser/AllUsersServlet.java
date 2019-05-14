package pl.qceyco.web.loggedUser.superUser;

import pl.qceyco.dao.AdminDao;
import pl.qceyco.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/admin/all-users")
public class AllUsersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        boolean isUserSuperAdmin = loggedAdmin.isSuperAdmin();
        if (isUserSuperAdmin == false) {
            response.sendRedirect("/app/dashboard?noAccessAsSuperUser=true");
            return;
        }
        request.setAttribute("admin", loggedAdmin);
        AdminDao adminDao = new AdminDao();
        List<Admin> admins = adminDao.getAllActiveAdmins();
        request.setAttribute("admins", admins);
        getServletContext().getRequestDispatcher("/superUser/usersAll.jsp").forward(request, response);
    }
}
