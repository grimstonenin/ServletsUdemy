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

@WebServlet(urlPatterns = {"/deleteServlet"})
public class DeleteUserServlet extends HttpServlet {

    private Connection connection;

    public void init(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:6603/mydb","root","mypassword");

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");

        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("DELETE from user where email='"+email+"'");

            PrintWriter writer = response.getWriter();

            if(result>0){
                writer.println("<h1>User Deleted</h1>");
            } else
            {
                writer.println("<h1>User not Deleted</h1>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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



}
