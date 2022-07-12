package com.xxyw.axios;

import com.google.gson.Gson;
import com.xxyw.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/axios02.do")
public class Demo02Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        str = sb.toString();

        Gson gson = new Gson();
        User user = gson.fromJson(str, User.class);
        System.out.println("user = " + user);

        user.setUname("宫本武藏");
        user.setPwd("886");

        String userJsonStr = gson.toJson(user);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(userJsonStr);
    }
}
