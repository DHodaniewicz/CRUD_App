package pl.coderslab.crud;

import pl.coderslab.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "delete", value = "/user/delete")
public class delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao dao = new UserDao();
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("userId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        dao.deleteUser(id);

        getServletContext().getRequestDispatcher("/user/list").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
