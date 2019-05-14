package pl.qceyco.web.loggedUser.userData;

import org.apache.commons.lang3.StringUtils;
import pl.qceyco.dao.AdminDao;
import pl.qceyco.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/edit-password")
public class EditUserPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        if (StringUtils.isBlank(password) || StringUtils.isBlank(repeatPassword)) {
            request.setAttribute("errorNotCompleteData", "Nie podano wszystkich danych!");
            doGet(request, response);
            return;
        }
        if (!password.equals(repeatPassword)) {
            request.setAttribute("errorIncorrectPassword", "Wprowadzone hasło zawiera błędy!");
            doGet(request, response);
            return;
        }
        HttpSession session = request.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        loggedAdmin.setPassword(password);
        AdminDao adminDao = new AdminDao();
        adminDao.updateAdmin(loggedAdmin);
        response.sendRedirect("/app/dashboard?successUserPasswordEdit=true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        request.setAttribute("admin", loggedAdmin);
        getServletContext().getRequestDispatcher("/user/editUserPassword.jsp")
                .forward(request, response);
    }
}
