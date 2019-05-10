package pl.qceyco.web;

import org.apache.commons.lang3.StringUtils;
import pl.qceyco.dao.AdminDao;
import pl.qceyco.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (StringUtils.isBlank(name) || StringUtils.isBlank(surname) || StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            request.setAttribute("errorNotCompleteData", "Nie podano wszystkich danych!");
            doGet(request,response);
            return;
        }
        Admin admin = new Admin(name,surname,email,password);
        AdminDao dao = new AdminDao();
        dao.createAdmin(admin);
        response.sendRedirect("/login");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
    }
}