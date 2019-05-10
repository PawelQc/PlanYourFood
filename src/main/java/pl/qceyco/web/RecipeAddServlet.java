package pl.qceyco.web;


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
import java.io.PrintWriter;


@WebServlet("/app/recipe/add")
public class RecipeAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Integer preparationTime = Integer.parseInt(request.getParameter("preparationTime"));
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");


        AdminDao adminDao = new AdminDao();

        HttpSession session = request.getSession();


        Integer id = (Integer) session.getAttribute("adminID");

        if (id != null) {

            Admin admin = adminDao.getAdminById(id);


            Recipe recipe = new Recipe(name, ingredients, description, preparationTime, preparation, admin);
            RecipeDao recipeDao = new RecipeDao();
            recipe = recipeDao.createRecipe(recipe);
            request.setAttribute("receipe", recipe);

            getServletContext().getRequestDispatcher("/app-recipes.jsp")
                    .forward(request, response);

        } else {
            PrintWriter printWriter = response.getWriter();
            printWriter.println("Id jest nulllem :(");

        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/recipeAddForm.jsp").forward(request, response);


    }
}
