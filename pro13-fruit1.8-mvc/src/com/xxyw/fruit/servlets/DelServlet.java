package com.xxyw.fruit.servlets;

import com.xxyw.fruit.dao.FruitDAO;
import com.xxyw.fruit.dao.impl.FruitDAOImpl;
import com.xxyw.fruit.pojo.Fruit;
import com.xxyw.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {

    FruitDAO fruitDao = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fid = Integer.parseInt(req.getParameter("fid"));
        fruitDao.delFruitById(fid);

        resp.sendRedirect("index");
    }
}
