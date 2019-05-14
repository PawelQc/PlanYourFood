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

@WebServlet("/app/admin/block-user")
public class BlockUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        request.setAttribute("admin", loggedAdmin);
        String userId = request.getParameter("userId");
        AdminDao adminDao = new AdminDao();
        Admin blockedAdmin = adminDao.getAdminById(Integer.parseInt(userId));
        blockedAdmin.setIsEnabledLogging(false);
        adminDao.updateAdmin(blockedAdmin);
        response.sendRedirect("/app/admin/all-users");
    }
}
