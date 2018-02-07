package com.bharath.userapp.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = {"/updateServlet"})
public class UpdateUserServlet extends HttpServlet{

    private Connection connection;

    public void init(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:6603/mydb", "root", "mypassword");
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }

    public void destroy(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");

        int result = 0;

        try {
            Statement statement = connection.createStatement();
            result =  statement.executeUpdate("Update user set password='"+password+"'WHERE email='"+email+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter out = httpServletResponse.getWriter();
            if(result>0){
                out.println("<h1>User Updated</h1>");
            } else
            {
                out.println("<h1>User not Updated</h1>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
