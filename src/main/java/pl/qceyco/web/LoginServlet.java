package pl.qceyco.web;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            request.setAttribute("errorNotCompleteData", "Nie podano wszystkich danych do logowania!");
            doGet(request, response);
            return;
        }
        AdminDao dao = new AdminDao();
        Admin admin = dao.isAdmininDB(email);
        boolean isLogged = dao.isPasswordCorrect(admin, password);
        if (isLogged == true) {
            HttpSession session = request.getSession();
            session.setAttribute("isLogged", "true");
            session.setAttribute("adminID", admin.getId());
            response.sendRedirect("/app/dashboard");
        } else {
            request.setAttribute("errorInvalidLogging", "Podany email lub hasło nie są prawidłowe!");
            doGet(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}