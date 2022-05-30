package com.xxyw.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Demo01Servlet extends HttpServlet {
    public Demo01Servlet() {
        System.out.println("构造函数...");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("初始化函数...");
    }

    @Override
    public void destroy() {
        System.out.println("销毁函数...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet()...");
    }
}
