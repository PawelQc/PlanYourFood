package pl.qceyco.web.loggedUser.userData;

import org.apache.commons.lang3.StringUtils;
import pl.qceyco.dao.AdminDao;
import pl.qceyco.dao.RecipeDao;
import pl.qceyco.model.Admin;
import pl.qceyco.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/edit-user-data")
public class EditUserDataServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName) || StringUtils.isBlank(email)) {
            request.setAttribute("errorNotCompleteData", "Nie podano wszystkich danych!");
            doGet(request, response);
            return;
        }
        HttpSession session = request.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        AdminDao adminDao = new AdminDao();
        loggedAdmin.setFirstName(firstName);
        loggedAdmin.setLastName(lastName);
        loggedAdmin.setEmail(email);
        adminDao.updateAdmin(loggedAdmin);
        response.sendRedirect("/app/dashboard?successUserDataEdit=true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin loggedAdmin = (Admin) session.getAttribute("loggedAdmin");
        request.setAttribute("admin", loggedAdmin);
        getServletContext().getRequestDispatcher("/user/editUserData.jsp")
                .forward(request, response);
    }
}
