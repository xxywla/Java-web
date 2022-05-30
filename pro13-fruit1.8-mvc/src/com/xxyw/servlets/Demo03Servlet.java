package com.xxyw.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
测试Session
 */
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        session.isNew();
        session.getMaxInactiveInterval();
        session.setMaxInactiveInterval(999);
        session.invalidate();
        session.setAttribute("name", "juyoujing");
        Object name = session.getAttribute("name");
        session.removeAttribute("name");
    }
}
