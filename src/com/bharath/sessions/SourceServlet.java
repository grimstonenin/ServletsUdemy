package com.bharath.sessions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/sourceServlet"},name = "sourceServlet")
public class SourceServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        for(Cookie c: cookies){
            System.out.println("Cookie name= "+c.getName());
            System.out.println("Cookie value= " + c.getValue());
        }

        String username = req.getParameter("userName");
        HttpSession session =req.getSession();
        session.setAttribute("user",username);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<a href='targetServlet'>Click here to get the user name!</a>");
    }
}
