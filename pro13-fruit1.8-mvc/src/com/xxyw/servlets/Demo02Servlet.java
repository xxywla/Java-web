package com.xxyw.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {"/demo02"},
        initParams = {
                @WebInitParam(name = "hello", value = "world"),
                @WebInitParam(name = "uname", value = "jim")
        })
public class Demo02Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println("initValue = " + initValue);
        initValue = config.getInitParameter("uname");
        System.out.println("initValue = " + initValue);
    }
}
