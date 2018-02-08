package com.bharath.loginapp.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


@WebServlet(urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(getServletContext().getInitParameter("dbUrl"),
                                                                getServletContext().getInitParameter("dbUser"),
                                                                getServletContext().getInitParameter("password"));

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE email='"+userName+"'"+" AND password='" + password +"';");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("homeServlet");

            if(resultSet.next()){
                req.setAttribute("message","Welcome to the application " + userName);

                requestDispatcher.forward(req,resp);

            } else {
                requestDispatcher = req.getRequestDispatcher("login.html");
                requestDispatcher.include(req,resp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
