package com.bharath.preinit.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "preInitServlet",urlPatterns = {"/preInitServlet"},loadOnStartup = 0)
//@WebServlet("/preInitServlet")
public class PreInitServlet extends HttpServlet{

    public void init(){
        System.out.println("Init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("From the preinit servlet");
    }
}
