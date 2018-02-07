package com.bharath.userapp.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet{

    private Connection connection;
    private PreparedStatement statement;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));

        try {
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setString(3,desc);
            statement.setInt(4,price);

            int result = statement.executeUpdate();

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            out.println("<b>"+result+" Products added</b>");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        ServletContext context = config.getServletContext();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(context.getInitParameter("dbUrl"), context.getInitParameter("dbUser"), context.getInitParameter("password"));
            statement = connection.prepareStatement("INSERT INTO product VALUES (?,?,?,?)");
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException c){
            c.printStackTrace();
        }

    }
}
