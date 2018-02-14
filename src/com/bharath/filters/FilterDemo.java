package com.bharath.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

//@WebFilter(filterName = "filter1",urlPatterns = {"/FilterServletDemo"})
public class FilterDemo implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PrintWriter out = servletResponse.getWriter();
        out.println("pre servlet - first filter");
        filterChain.doFilter(servletRequest,servletResponse);
        out.println("post servlet - first filter");
    }

    @Override
    public void destroy() {

    }
}
