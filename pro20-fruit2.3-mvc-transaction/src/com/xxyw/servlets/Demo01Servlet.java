package com.xxyw.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println("initValue = " + initValue);

        ServletContext servletContext = getServletContext();
        String location = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("location = " + location);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        String location = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("location = " + location);

        ServletContext servletContext1 = req.getSession().getServletContext();
        String location1 = servletContext1.getInitParameter("contextConfigLocation");
        System.out.println("location1 = " + location1);
    }
}
