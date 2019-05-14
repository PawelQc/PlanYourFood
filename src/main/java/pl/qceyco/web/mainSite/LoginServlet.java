package pl.qceyco.web.mainSite;

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
        if (admin == null) {
            request.setAttribute("errorInvalidLogging", "Podany email nie istnieje w bazie danych! Zarejestruj się.");
            doGet(request, response);
            return;
        }
        if (admin.isEnabledLogging() == false) {
            request.setAttribute("errorBlockedUser", "Podany użytkownik został zablokowany przez administratora. Logowanie nie jest możliwe!");
            doGet(request, response);
            return;
        }
        boolean isLogged = dao.isPasswordCorrect(admin, password);
        if (isLogged == true) {
            HttpSession session = request.getSession();
            session.setAttribute("isLogged", "true");
            session.setAttribute("loggedAdmin", admin);
            response.sendRedirect("/app/dashboard");
        } else {
            request.setAttribute("errorInvalidLogging", "Podane hasło nie jest prawidłowe! Spróbuj ponownie.");
            doGet(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String successfulRegister = request.getParameter("register");
        if (StringUtils.isNotBlank(successfulRegister) && successfulRegister.equals("true")) {
            request.setAttribute("successfulRegister", "Udana rejestracja!");
        }
        getServletContext().getRequestDispatcher("/unloggedUser/login.jsp").forward(request, response);
    }
}