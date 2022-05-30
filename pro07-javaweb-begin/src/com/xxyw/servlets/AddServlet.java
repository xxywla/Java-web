package com.xxyw.servlets;

import com.xxyw.fruit.dao.FruitDAO;
import com.xxyw.fruit.dao.impl.FruitDAOImpl;
import com.xxyw.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = req.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");

        //System.out.println("fname = " + fname);
        //System.out.println("price = " + price);
        //System.out.println("fcount = " + fcount);
        //System.out.println("remark = " + remark);

        FruitDAO fruitDAO = new FruitDAOImpl();
        boolean res = fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));
        System.out.println(res ? "添加成功" : "添加失败！");
    }
}
