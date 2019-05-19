package myweb.controller;

import myweb.domain.User;
import myweb.model.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends HttpServlet {
    public LoginController(){super();}
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher requestDispatcher = null;

        DBManager dbManager = new DBManager();
        User user = dbManager.authenticate(username, password);
        if (user != null) {
            requestDispatcher = request.getRequestDispatcher("/success.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        else {
            requestDispatcher = request.getRequestDispatcher("/error.jsp");
        }
        requestDispatcher.forward(request, response);


    }
}
