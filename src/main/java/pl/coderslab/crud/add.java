package pl.coderslab.crud;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "add", value = "/user/add")
public class add extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/user/add.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao dao = new UserDao();
        response.setContentType("text/html");

        String userName = request.getParameter("userName");
        String userMail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        if (userName != null & userMail != null & userPassword != null) {
            User user = new User(userName, userMail, userPassword);
            dao.create(user);
            PrintWriter writer = response.getWriter();

        } else {
            System.out.println("Niekompletne dane!");
            PrintWriter writer = response.getWriter();
            writer.append("alert(\"Niekompletne dane!\");");
        }

        response.sendRedirect(request.getContextPath() + "/user/list");


    }
}
