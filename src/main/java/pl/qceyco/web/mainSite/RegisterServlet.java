package pl.qceyco.web.mainSite;

import org.apache.commons.lang3.StringUtils;
import pl.qceyco.dao.AdminDao;
import pl.qceyco.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        if (StringUtils.isBlank(name) || StringUtils.isBlank(surname) || StringUtils.isBlank(email) || StringUtils.isBlank(password) || StringUtils.isBlank(repeatPassword)) {
            request.setAttribute("errorNotCompleteData", "Nie podano wszystkich danych!");
            doGet(request, response);
            return;
        }
        if (!password.equals(repeatPassword)) {
            request.setAttribute("errorIncorrectPassword", "Wprowadzone hasło zawiera błędy!");
            doGet(request, response);
            return;
        }
        boolean regex = Pattern.matches("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}", email);
        if (regex == false) {
            request.setAttribute("errorIncorrectEmailFormat", "Nieprawidłowy email. Wprowadź poprawny zapis adresu email!");
            doGet(request, response);
            return;
        }
        AdminDao dao = new AdminDao();
        List<Admin> admins = dao.getAllAdmins();
        boolean isEmailInDB = false;
        for (Admin a : admins) {
            if (a.getEmail().equals(email)) {
                isEmailInDB = true;
                break;
            }
        }
        if (isEmailInDB == true) {
            request.setAttribute("errorEmailAlreadyInDB", "Podany adres email już został wcześniej zarejestrowany!");
            doGet(request, response);
            return;
        }
        Admin admin = new Admin(name, surname, email, password, false, true);
        dao.createAdmin(admin);
        response.sendRedirect("/login?register=true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/unloggedUser/registration.jsp").forward(request, response);
    }

}