package com.bharath.userapp.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;

//@WebServlet(urlPatterns = {"/readServlet"})
public class ReadUsersServlet extends HttpServlet {

    private Connection connection;


    public void init(ServletConfig config) {
        System.out.println("init");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ServletContext context = config.getServletContext();

        Enumeration<String> params = context.getInitParameterNames();

        while(params.hasMoreElements()){
            String paramName = params.nextElement();
            System.out.println("Parameter name: " + paramName + " ----> Value: " + context.getInitParameter(paramName));
        }



        try {
            connection = DriverManager.getConnection(context.getInitParameter("dbUrl"), context.getInitParameter("dbUser"), context.getInitParameter("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void destroy() {
        System.out.println("destroy");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        System.out.println("doGet");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            PrintWriter writer = httpServletResponse.getWriter();
            writer.println("<table>");
            writer.println("<tr>");
            writer.println("<th>");
            writer.println("First Name");
            writer.println("</th>");
            writer.println("<th>");
            writer.println("Last Name");
            writer.println("</th>");
            writer.println("<th>");
            writer.println("Email");
            writer.println("</th>");
            writer.println("</tr");
            while (resultSet.next()) {
                writer.println("<tr>");
                writer.println("<td>");
                writer.println(resultSet.getString(1));
                writer.println("</td>");
                writer.println("<td>");
                writer.println(resultSet.getString(2));
                writer.println("</td>");
                writer.println("<td>");
                writer.println(resultSet.getString(3));
                writer.println("</td>");
                writer.println("</tr>");
            }
            writer.println("</table");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
