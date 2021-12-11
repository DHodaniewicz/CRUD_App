package pl.coderslab.crud;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "edit", value = "/user/edit")
public class edit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            UserDao userDao = new UserDao();
            user = userDao.read(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        request.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/user/edit.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao dao = new UserDao();
        response.setContentType("text/html");

        int id = - 1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String userName = request.getParameter("userName");
        String userMail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        if (userName != null && userMail != null && userPassword != null && id != -1) {
            User user = dao.read(id);
            user.setUserName(userName);
            user.setEmail(userMail);
            user.setPassword(userPassword);
            dao.update(user);
        } else {
            System.out.println("Niekompletne dane!");
        }

        response.sendRedirect(request.getContextPath() + "/user/list");

    }
}
